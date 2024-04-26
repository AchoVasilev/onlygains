package com.project.authentication.models

import io.micronaut.serde.annotation.Serdeable
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty

@Serdeable
data class RegisterUserRequestResource(
    @NotEmpty @Email val email: String,
    @NotEmpty val password: String,
    @NotEmpty val firstName: String,
    @NotEmpty val lastName: String
)
