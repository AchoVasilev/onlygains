package com.project.infrastructure.security.token

data class RefreshTokenResource(
    val accessToken: String,
    val tokenType: String,
    val expiresIn: Int,
    val refreshToken: String,
)
