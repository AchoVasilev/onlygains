package com.project.infrastructure.security.token

import com.project.common.errormessages.UserMessages
import com.project.common.result.OperationResult
import com.project.common.result.ResultStatus
import com.project.infrastructure.security.jwt.JwtService
import com.project.infrastructure.security.refresh.RefreshTokenService
import io.micronaut.security.authentication.Authentication
import jakarta.inject.Singleton

@Singleton
open class TokenGenerator(private val refreshTokenService: RefreshTokenService, private val jwtService: JwtService) {
    open fun generateJwt(authentication: Authentication): AccessTokenResource {
        val jwt = this.jwtService.generate(authentication)
        val payload = this.refreshTokenService.generateKey()
        val refreshToken = this.refreshTokenService.generateToken(authentication, payload)

        val bearerType = "Bearer"
        val expiration = this.jwtService.getExpiration()

        return AccessTokenResource(jwt, bearerType, expiration, refreshToken, authentication.name, authentication.roles)
    }

    open fun refreshToken(refreshToken: String): OperationResult<RefreshTokenResource> {
        val result = this.refreshTokenService.validateToken(refreshToken)
        if (result.isBlank()) {
            return OperationResult.failure(UserMessages.AUTHENTICATION_FAILED.toError(), ResultStatus.Invalid)
        }

        val auth = this.refreshTokenService.getAuthentication(refreshToken)
    }
}