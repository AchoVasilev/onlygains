package com.project.authentication.models

import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class RefreshTokenResponseResource(
    val accessToken: String?,
    val tokenType: String?,
    val expiresIn: Long?,
    val refreshToken: String?,
    val extensions: Map<String, Any>?
)