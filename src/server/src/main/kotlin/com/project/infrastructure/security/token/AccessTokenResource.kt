package com.project.infrastructure.security.token

data class AccessTokenResource(
    val accessToken: String,
    val tokenType: String,
    val expiresIn: Int,
    val refreshToken: String,
    val username: String,
    val roles: Collection<String>
)