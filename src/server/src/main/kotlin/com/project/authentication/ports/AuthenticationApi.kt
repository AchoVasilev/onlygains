package com.project.authentication.ports

import com.project.authentication.models.LoginRequestResource
import com.project.authentication.models.AccessTokenResponseResource
import com.project.infrastructure.exceptions.HttpErrorResponse
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Post
import io.micronaut.security.annotation.Secured
import io.micronaut.security.rules.SecurityRule
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import jakarta.validation.Valid

interface AuthenticationApi {
    @Operation(
        summary = "MyActivePal: Login endpoint",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "Ok",
                content = arrayOf(Content(schema = Schema(implementation = AccessTokenResponseResource::class)))
            ),
            ApiResponse(
                responseCode = "400",
                description = "Bad Request",
                content = arrayOf(
                    Content(schema = Schema(implementation = HttpErrorResponse::class))
                )
            ),
            ApiResponse(
                responseCode = "404",
                description = "Not Found",
                content = arrayOf(
                    Content(schema = Schema(implementation = HttpErrorResponse::class))
                )
            ),
            ApiResponse(
                responseCode = "500",
                description = "Internal Server Error",
                content = arrayOf(
                    Content(schema = Schema(implementation = HttpErrorResponse::class))
                )
            ),
            ApiResponse(
                responseCode = "503",
                description = "Service Unavailable",
                content = arrayOf(
                    Content(schema = Schema(implementation = HttpErrorResponse::class))
                )
            )
        ]
    )

    @Secured(SecurityRule.IS_ANONYMOUS)
    @Post("/login")
    fun login(@Body @Valid loginRequest: LoginRequestResource): HttpResponse<AccessTokenResponseResource>

    @Post("/refresh")
    fun refresh(): HttpResponse<AccessTokenResponseResource>
}