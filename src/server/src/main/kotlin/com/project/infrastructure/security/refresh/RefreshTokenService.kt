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
import com.project.infrastructure.exceptions.exceptions.TokenException
import com.project.infrastructure.security.refresh.data.RefreshToken
import com.project.infrastructure.security.refresh.data.RefreshTokenRepository
import com.project.infrastructure.utilities.LoggerProvider
import io.micronaut.security.authentication.Authentication
import io.micronaut.transaction.annotation.Transactional
import jakarta.inject.Singleton
import java.util.Base64
import java.util.UUID
import kotlin.text.Charsets.UTF_8

@Singleton
open class RefreshTokenService(
    private val refreshTokenRepository: RefreshTokenRepository,
    private val config: RefreshTokenConfiguration,
    private val userService: UserService
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

    fun generateKey(): String {
        return UUID.randomUUID().toString()
    }

    private fun persistToken(userName: String, serializedToken: String) {
        val user = this.userService.findUserBy(userName) ?: throw TokenException()
        val token = RefreshToken(serializedToken, user.id, this.config.getExpirationTime())
        this.refreshTokenRepository.save(token)
    }

    companion object {
        @JvmStatic
        private val log = LoggerProvider.getLogger(RefreshTokenService::class.java)
    }
}