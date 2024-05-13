package com.project.authentication.models

import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class AuthModel(val username: String, val roles: Collection<String>)
