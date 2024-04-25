package com.project.authentication

import com.project.authentication.models.LoginRequestResource
import com.project.security.jwt.JwtService
import jakarta.inject.Singleton

@Singleton
open class AuthenticationService(private val jwtService: JwtService) {

    open fun authenticate(loginRequest: LoginRequestResource): String {

    }
}