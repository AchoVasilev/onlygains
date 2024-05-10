package com.project.infrastructure.security.refresh

import com.nimbusds.jose.JOSEException
import com.nimbusds.jose.JWSAlgorithm
import com.nimbusds.jose.JWSHeader
import com.nimbusds.jose.JWSObject
import com.nimbusds.jose.JWSSigner
import com.nimbusds.jose.JWSVerifier
import com.nimbusds.jose.Payload
import com.nimbusds.jose.crypto.MACSigner
import com.nimbusds.jose.crypto.MACVerifier
import com.project.authentication.UserService
import com.project.domain.user.UserStatus
import com.project.infrastructure.exceptions.base.ErrorCode
import com.project.infrastructure.exceptions.exceptions.TokenException
import com.project.infrastructure.security.EmailEncryptionService
import com.project.infrastructure.security.refresh.data.RefreshToken
import com.project.infrastructure.security.refresh.data.RefreshTokenRepository
import com.project.infrastructure.utilities.LoggerProvider
import io.micronaut.security.authentication.Authentication
import io.micronaut.transaction.annotation.Transactional
import jakarta.inject.Singleton
import org.apache.http.ParseException
import java.time.Instant
import java.util.Base64
import java.util.UUID
import kotlin.text.Charsets.UTF_8

@Singleton
open class RefreshTokenService(
    private val refreshTokenRepository: RefreshTokenRepository,
    private val config: RefreshTokenConfiguration,
    private val userService: UserService,
    private val emailEncryptionService: EmailEncryptionService
) {
    private val algorithm: JWSAlgorithm = this.config.jwsAlgorithm
    private val signer: JWSSigner
    private val verifier: JWSVerifier

    init {
        val secret =
            if (config.isBase64) Base64.getDecoder().decode(config.secret) else config.secret.toByteArray(UTF_8)
        this.signer = MACSigner(secret)
        this.verifier = MACVerifier(secret)
    }

    @Transactional
    open fun generateToken(authentication: Authentication, payload: String): String {
        log.info("Generating refresh token")
        val jwsObject = JWSObject(JWSHeader(this.algorithm), (Payload("${payload}-${authentication.name}")))
        try {
            jwsObject.sign(this.signer)
            val serialized = jwsObject.serialize()
            this.persistToken(authentication.name, serialized)

            return serialized
        } catch (ex: IllegalStateException) {
            log.error("JWS is in unsigned state", ex)
            throw TokenException()
        } catch (ex: JOSEException) {
            log.error("Signing exception", ex)
            throw TokenException()
        }
    }

    /**
     * Returns the parsed token's payload or an empty String if token is not verified
     */
    fun validateToken(token: String): String {
        val jwsObject: JWSObject?
        try {
            jwsObject = JWSObject.parse(token)
            return if (jwsObject.verify(this.verifier)) {
                jwsObject.payload.toString()
            } else ""

        } catch (ex: ParseException) {
            log.error("JWS parsing exception", ex)
            throw TokenException()
        } catch (ex: IllegalStateException) {
            log.error("JWS could is not in a signed or verified state", ex)
            throw TokenException(ErrorCode.TOKEN_VERIFICATION_EXCEPTION)
        } catch (ex: JOSEException) {
            log.error("JWS could not be verified", ex)
            throw TokenException(ErrorCode.TOKEN_VERIFICATION_EXCEPTION)
        }
    }

    fun generateKey(): String {
        return UUID.randomUUID().toString()
    }

    @Transactional
    open fun getAuthentication(validRefreshToken: String): Authentication {
        val refreshToken = this.refreshTokenRepository.findByToken(validRefreshToken)
        if (refreshToken == null || refreshToken.isRevoked) {
            log.error("Invalid refresh token. [token={}]", validRefreshToken)

            refreshToken?.userId?.let { userId ->
                {
                    log.warn(
                        "Deleting invalid/revoked refresh tokens. [tokenId={}, userId={}]",
                        refreshToken.id,
                        userId
                    )
                    this.refreshTokenRepository.deleteAllByUserId(userId)
                }
            }

            throw TokenException(ErrorCode.TOKEN_INVALIDATION_EXCEPTION)
        }

        val user = this.userService.findUserBy(refreshToken.userId!!)
        if (user == null || UserStatus.DEACTIVATED == user.status) {
            log.error("User is null or is deactivated. [userId={}]", user?.id)

            refreshToken.userId?.let { userId ->
                {
                    log.warn("Deleting user refresh tokens. [tokenId={}, userId={}]", refreshToken.id, userId)
                    this.refreshTokenRepository.deleteAllByUserId(userId)
                }
            }

            throw TokenException(ErrorCode.TOKEN_INVALIDATION_EXCEPTION)
        }

        val decryptedEmail = this.emailEncryptionService.decryptEmail(user.email)

        return Authentication.build(decryptedEmail, listOf(user.role.name))
    }

    @Transactional
    open fun revokeToken(validRefreshToken: String) {
        val refreshToken = this.refreshTokenRepository.findByToken(validRefreshToken)
        if (refreshToken == null || refreshToken.isRevoked) {
            log.error("Could not revoke token. [tokenId={}]", refreshToken?.id)
            throw TokenException(ErrorCode.TOKEN_INVALIDATION_EXCEPTION)
        }

        refreshToken.revoke()
        this.refreshTokenRepository.save(refreshToken)
        log.info("Token revoked. [tokenId={}]", refreshToken.id)
    }

    fun getExpiration(): Long {
        return this.config.getExpirationTime()
    }

    private fun persistToken(userName: String, serializedToken: String) {
        val user = this.userService.findUserBy(userName) ?: throw TokenException()
        val token = RefreshToken(serializedToken, user.id, Instant.now().plusSeconds(this.config.getExpirationTime()))
        this.refreshTokenRepository.save(token)
    }

    companion object {
        @JvmStatic
        private val log = LoggerProvider.getLogger(RefreshTokenService::class.java)
    }
}