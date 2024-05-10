package com.project.authentication.models

import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class AccessTokenResponseResource(
    val accessToken: String?,
    val tokenType: String?,
    val expiresIn: Int?,
    val refreshToken: String?,
    val username: String?,
    val roles: Collection<String>?,
    val extensions: Map<String, Any>?
)
