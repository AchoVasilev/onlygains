package com.project.infrastructure.security.token

enum class TokenType(val value: String) {
    Bearer("Bearer"),
    RefreshToken("refresh_token");
}