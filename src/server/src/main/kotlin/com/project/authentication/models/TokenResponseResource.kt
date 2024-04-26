package com.project.authentication.models

import io.micronaut.serde.annotation.Serdeable
import java.util.Date

@Serdeable
data class TokenResponseResource(val token: String, val expiresInSeconds: Long, val expiresAt: Date)
