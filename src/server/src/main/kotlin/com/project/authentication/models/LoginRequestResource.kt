package com.project.authentication.models

import io.micronaut.serde.annotation.Serdeable
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty

@Serdeable
data class LoginRequestResource(@NotEmpty @Email val email: String, @NotEmpty val password: String)
