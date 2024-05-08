package com.project.infrastructure.security.refresh

import com.project.common.result.OperationResult
import com.project.infrastructure.security.refresh.data.RefreshTokenRepository
import io.micronaut.security.authentication.Authentication
import jakarta.inject.Singleton

@Singleton
open class RefreshTokenService(private val refreshTokenRepository: RefreshTokenRepository) {
    open fun generateToken(authentication: Authentication): OperationResult<String> {

    }
}