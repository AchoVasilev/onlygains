package com.project.authentication

import com.project.application.services.LoggerProvider
import com.project.authentication.models.LoginRequestResource
import com.project.common.errormessages.UserMessages
import com.project.common.result.OperationResult
import com.project.common.result.ResultStatus
import com.project.domain.user.User
import com.project.domain.user.UserStatus
import com.project.security.jwt.JwtService
import io.micronaut.security.authentication.Authentication
import io.micronaut.security.utils.SecurityService
import io.micronaut.transaction.annotation.Transactional
import jakarta.inject.Singleton

@Singleton
open class AuthenticationService(
    private val jwtService: JwtService,
    private val userService: UserService,
    private val securityService: SecurityService
) {

    @Transactional(readOnly = true)
    open fun authenticate(loginRequest: LoginRequestResource): OperationResult<String> {
        val userResult = this.userService.findUserBy(loginRequest.email, loginRequest.password)

        if (userResult.isFailure) {
            return OperationResult.failure(userResult.error, userResult.status)
        }

        val user = userResult.value()
        if (UserStatus.ACTIVE != user.status) {
            log.info("User is deactivated. [userId={}, email={}]", user.id, loginRequest.email)
            return OperationResult.failure(UserMessages.USER_DEACTIVATED.toError(), ResultStatus.Invalid)
        }

        log.info("Authenticating user. [userId={}, email={}]", user.id, loginRequest.email)

        return OperationResult.success(this.generateToken(user, loginRequest.email))
    }

    @Transactional(readOnly = true)
    open fun refreshToken(): OperationResult<String> {
        val auth = this.securityService.authentication.orElse(null)
        if (auth == null) {
            log.info("Cannot issue refresh token for an unauthenticated user")
            return OperationResult.failure(
                UserMessages.AUTHENTICATION_FAILED.toError(),
                ResultStatus.Unauthorized
            )
        }

        val user = this.userService.findUserBy(auth.name)
        if (user == null) {
            log.info("Cannot issue refresh token for non existing user. [email={}]", auth.name)
            return OperationResult.failure(UserMessages.USER_NOT_EXIST.toError(), ResultStatus.Unauthorized)
        }

        if (UserStatus.ACTIVE != user.status) {
            log.info("Cannot issue refresh token for deactivated user. [email={}]", auth.name)
            return OperationResult.failure(UserMessages.USER_DEACTIVATED.toError(), ResultStatus.Unauthorized)
        }

        return OperationResult.success(this.generateToken(user, auth.name))
    }

    private fun generateToken(user: User, email: String): String {
        val authentication = this.createAuthentication(user, email)
        return this.jwtService.generate(authentication)
    }

    private fun createAuthentication(user: User, email: String): Authentication {
        return Authentication.build(email, setOf(user.role.name))
    }

    companion object {
        @JvmStatic
        private val log = LoggerProvider.getLogger(AuthenticationService::class.java)
    }
}