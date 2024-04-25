package com.project.authentication.models

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty

data class LoginRequestResource(@NotEmpty @Email val email: String, @NotEmpty val password: String)
