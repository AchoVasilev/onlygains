package com.project.infrastructure.security.token


data class AccessRefreshTokenResource(val accessToken: String, val tokenType: String, val refreshToken: String, val expiresIn: Int)