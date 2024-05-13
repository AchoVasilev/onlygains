package com.project.authentication.ports

import com.project.authentication.models.AccessTokenResponseResource
import com.project.authentication.models.AuthModel
import com.project.authentication.models.LoginRequestResource
import com.project.authentication.models.RefreshTokenResponseResource
import com.project.infrastructure.exceptions.HttpErrorResponse
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.security.authentication.Authentication
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.parameters.RequestBody
import io.swagger.v3.oas.annotations.responses.ApiResponse
import jakarta.validation.Valid

interface AuthenticationApi {
    @Operation(
        summary = "MyActivePal: Login endpoint",
        requestBody = RequestBody(
            description = "Login credentials",
            content = arrayOf(Content(schema = Schema(implementation = LoginRequestResource::class)))
        ),
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

    @Post("/login")
    fun login(@Body @Valid loginRequest: LoginRequestResource): HttpResponse<AccessTokenResponseResource>

    @Operation(
        summary = "MyActivePal: Token refresh endpoint",
        requestBody = RequestBody(
            description = "Refresh token data",
            content = arrayOf(
                Content(
                    schema = Schema(name = "grant_type", defaultValue = "refresh_token"),
                    additionalPropertiesSchema = Schema(
                        name = "refresh_token",
                        description = "value of the refresh token"
                    ),
                )
            )
        ),
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "Ok",
                content = arrayOf(Content(schema = Schema(implementation = RefreshTokenResponseResource::class)))
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
    @Post("/refresh")
    fun refresh(@Body body: Map<String, String>): HttpResponse<RefreshTokenResponseResource>

    @Operation(
        summary = "MyActivePal: User is authenticated endpoint",
        description = "Retrieve authenticated user data or null",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "Ok",
                content = arrayOf(Content(schema = Schema(implementation = AuthModel::class)))
            ),
            ApiResponse(
                responseCode = "503",
                description = "Service Unavailable",
                content = arrayOf(
                    Content(schema = Schema(implementation = HttpErrorResponse::class))
                )
            )]
    )
    @Get
    fun isAuthenticated(authentication: Authentication?): HttpResponse<AuthModel>

    @Operation(
        summary = "MyActivePal: User log out response",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "Ok",
                content = arrayOf(Content(schema = Schema(implementation = AuthModel::class)))
            ),
            ApiResponse(
                responseCode = "400",
                description = "Bad Request",
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
    @Post("/logout")
    fun logOut(authentication: Authentication): HttpResponse<Unit>
}