package com.project.authentication.ports

import com.nimbusds.jwt.SignedJWT
import com.project.authentication.AuthenticationService
import com.project.authentication.models.LoginRequestResource
import com.project.authentication.models.TokenResponseResource
import com.project.common.result.OperationResult
import io.micronaut.context.annotation.Value
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.security.annotation.Secured
import io.micronaut.security.rules.SecurityRule
import jakarta.validation.Valid
import java.util.Date

@Controller("/auth")
@Secured(SecurityRule.IS_AUTHENTICATED)
open class AuthenticationController(
    @Value("\${jwt.expirationTimeInSeconds}") private val jwtExpirationInSeconds: Long,
    private val authenticationService: AuthenticationService
) {

    @Secured(SecurityRule.IS_ANONYMOUS)
    @Post("/login")
    open fun login(@Body @Valid loginRequest: LoginRequestResource): HttpResponse<TokenResponseResource> {
        val authenticationResult = this.authenticationService.authenticate(loginRequest)

        return this.buildTokenResponse(authenticationResult)
    }

    @Post("/refresh")
    open fun refresh(): HttpResponse<TokenResponseResource> {
        val refreshResult = this.authenticationService.refreshToken()

        return this.buildTokenResponse(refreshResult)
    }

    private fun buildTokenResponse(tokenResult: OperationResult<String>): HttpResponse<TokenResponseResource> {
        if (tokenResult.isFailure) {
            return HttpResponse.status(tokenResult.status.toHttpStatus())
        }

        val tokenResponse = TokenResponseResource(
            tokenResult.value(), this.jwtExpirationInSeconds, this.getTokenExpirationAt(
            tokenResult.value()
        ))

        return HttpResponse.status<TokenResponseResource?>(HttpStatus.OK)
            .body(tokenResponse)
            .header("X-AUTH-TOKEN", tokenResult.value())
    }

    private fun getTokenExpirationAt(token: String): Date {
        val jwt = SignedJWT.parse(token)
        return jwt.jwtClaimsSet.expirationTime
    }
}