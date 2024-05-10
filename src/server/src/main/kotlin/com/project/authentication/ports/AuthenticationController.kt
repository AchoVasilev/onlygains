package com.project.authentication.ports

import com.project.authentication.AuthenticationService
import com.project.authentication.models.AccessTokenResponseResource
import com.project.authentication.models.LoginRequestResource
import com.project.common.result.OperationResult
import com.project.infrastructure.security.token.AccessTokenResource
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.security.annotation.Secured
import io.micronaut.security.rules.SecurityRule
import jakarta.validation.Valid

@Controller("/auth")
@Secured(SecurityRule.IS_AUTHENTICATED)
open class AuthenticationController(
    private val authenticationService: AuthenticationService
) : AuthenticationApi {

    override fun login(@Body @Valid loginRequest: LoginRequestResource): HttpResponse<AccessTokenResponseResource> {
        val authenticationResult = this.authenticationService.authenticate(loginRequest)

        return this.buildJwtResponse(authenticationResult)
    }

    override fun refresh(): HttpResponse<AccessTokenResponseResource> {
        val refreshResult = this.authenticationService.refreshToken()

        return this.buildJwtResponse(refreshResult)
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
}