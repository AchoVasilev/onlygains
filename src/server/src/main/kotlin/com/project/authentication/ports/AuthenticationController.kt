package com.project.authentication.ports

import com.project.authentication.AuthenticationService
import com.project.authentication.models.AccessTokenResponseResource
import com.project.authentication.models.AuthModel
import com.project.authentication.models.LoginRequestResource
import com.project.authentication.models.RefreshTokenResponseResource
import com.project.common.Constants.GRANT_TYPE
import com.project.common.result.OperationResult
import com.project.infrastructure.exceptions.base.ErrorCode
import com.project.infrastructure.exceptions.exceptions.TokenException
import com.project.infrastructure.security.token.AccessTokenResource
import com.project.infrastructure.security.token.RefreshTokenResource
import com.project.infrastructure.security.token.TokenType
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.security.annotation.Secured
import io.micronaut.security.authentication.Authentication
import io.micronaut.security.rules.SecurityRule
import jakarta.validation.Valid

@Controller("/auth")
@Secured(SecurityRule.IS_ANONYMOUS)
open class AuthenticationController(
    private val authenticationService: AuthenticationService
) : AuthenticationApi {

    override fun login(@Body @Valid loginRequest: LoginRequestResource): HttpResponse<AccessTokenResponseResource> {
        val authenticationResult = this.authenticationService.authenticate(loginRequest)

        return this.buildJwtResponse(authenticationResult)
    }

    override fun refresh(body: Map<String, String>): HttpResponse<RefreshTokenResponseResource> {
        val grantType = body[GRANT_TYPE]
        val refreshToken = body[TokenType.RefreshToken.value]
        if (grantType == null || refreshToken == null) {
            throw TokenException(ErrorCode.TOKEN_GRANT_TYPE_EXCEPTION)
        }

        val refreshResult = this.authenticationService.refreshToken(refreshToken)

        return this.buildRefreshTokenResponse(refreshResult)
    }

    override fun isAuthenticated(authentication: Authentication?): HttpResponse<AuthModel> {
        if (authentication == null) {
            return HttpResponse.ok()
        }

        return HttpResponse.ok(AuthModel(authentication.name, authentication.roles))
    }

    @Secured(SecurityRule.IS_AUTHENTICATED)
    override fun logOut(authentication: Authentication): HttpResponse<Unit> {
        this.authenticationService.logout(authentication)

        return HttpResponse.ok()
    }

    private fun buildJwtResponse(operationResult: OperationResult<AccessTokenResource>): HttpResponse<AccessTokenResponseResource> {
        if (operationResult.isFailure) {
            return HttpResponse.status<AccessTokenResponseResource?>(operationResult.status.toHttpStatus())
                .body(
                    AccessTokenResponseResource(
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        mapOf(operationResult.error.code to operationResult.error.description)
                    )
                )
        }

        val accessToken = operationResult.value()
        val tokenResponse = AccessTokenResponseResource(
            accessToken.accessToken,
            accessToken.tokenType,
            accessToken.expiresIn,
            accessToken.refreshToken,
            accessToken.username,
            accessToken.roles,
            emptyMap()
        )

        return HttpResponse.status<AccessTokenResponseResource?>(HttpStatus.OK)
            .body(tokenResponse)
            .header("X-AUTH-TOKEN", accessToken.accessToken)
    }

    private fun buildRefreshTokenResponse(operationResult: OperationResult<RefreshTokenResource>): HttpResponse<RefreshTokenResponseResource> {
        if (operationResult.isFailure) {
            return HttpResponse.status<RefreshTokenResponseResource?>(operationResult.status.toHttpStatus()).body(
                RefreshTokenResponseResource(
                    null,
                    null,
                    null,
                    null,
                    mapOf(operationResult.error.code to operationResult.error.description)
                )
            )
        }

        val refreshToken = operationResult.value()
        val responseResource = RefreshTokenResponseResource(
            refreshToken.accessToken,
            refreshToken.tokenType,
            refreshToken.expiresIn,
            refreshToken.refreshToken,
            emptyMap()
        )

        return HttpResponse.ok(responseResource)
    }
}