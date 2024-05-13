package com.project.authentication.models

import io.micronaut.serde.annotation.Serdeable
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Pattern

@Serdeable
data class RegisterUserRequestResource(
    @NotEmpty @Email val email: String,
    @Pattern(regexp = "^(?=[^A-Z]*[A-Z])(?=[^a-z]*[a-z])(?=\\D*\\d).{8,}$")
    @NotEmpty val password: String,
    @NotEmpty val repeatPassword: String,
    @NotEmpty val firstName: String,
    @NotEmpty val lastName: String
)
