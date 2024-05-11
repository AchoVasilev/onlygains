package com.project.infrastructure.security.token

import com.project.common.errormessages.UserMessages
import com.project.common.result.OperationResult
import com.project.common.result.ResultStatus
import com.project.infrastructure.security.jwt.JwtService
import com.project.infrastructure.security.refresh.RefreshTokenService
import com.project.infrastructure.utilities.TransactionExecutor
import io.micronaut.security.authentication.Authentication
import jakarta.inject.Singleton

@Singleton
open class TokenGenerator(
    private val refreshTokenService: RefreshTokenService,
    private val jwtService: JwtService,
    private val transactionExecutor: TransactionExecutor
) {
    open fun generateJwt(authentication: Authentication): AccessTokenResource {
        val jwt = this.jwtService.generate(authentication)
        val payload = this.refreshTokenService.generateKey()
        val refreshToken = this.refreshTokenService.generateToken(authentication, payload)

        val expiration = this.jwtService.getExpiration()

        return AccessTokenResource(
            jwt,
            TokenType.Bearer.value,
            expiration,
            refreshToken,
            authentication.name,
            authentication.roles
        )
    }

    open fun refreshToken(refreshToken: String): OperationResult<RefreshTokenResource> {
        val refreshTokenPayload = this.refreshTokenService.validateToken(refreshToken)
        if (refreshTokenPayload.isBlank()) {
            return OperationResult.failure(UserMessages.AUTHENTICATION_FAILED.toError(), ResultStatus.Forbidden)
        }

        val auth = this.transactionExecutor.runInTransaction {
            this.refreshTokenService.getAuthentication(refreshToken)
        }

        val newRefreshToken = this.transactionExecutor.runInTransaction {
            this.refreshTokenService.revokeToken(refreshToken)
            val payload = this.refreshTokenService.generateKey()
            this.refreshTokenService.generateToken(auth, payload)
        }

        val jwt = this.jwtService.generate(auth)

        return OperationResult.success(
            RefreshTokenResource(
                jwt,
                TokenType.RefreshToken.value,
                this.refreshTokenService.getExpiration(),
                newRefreshToken
            )
        )
    }
}