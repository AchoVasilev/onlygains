package com.project.authentication

import com.project.authentication.models.LoginRequestResource
import com.project.common.errormessages.UserMessages
import com.project.common.result.OperationResult
import com.project.common.result.ResultStatus
import com.project.domain.user.User
import com.project.domain.user.UserStatus
import com.project.infrastructure.security.token.AccessTokenResource
import com.project.infrastructure.security.token.RefreshTokenResource
import com.project.infrastructure.security.token.TokenGenerator
import com.project.infrastructure.utilities.LoggerProvider
import io.micronaut.security.authentication.Authentication
import io.micronaut.transaction.annotation.Transactional
import jakarta.inject.Singleton

@Singleton
open class AuthenticationService(
    private val tokenGenerator: TokenGenerator,
    private val userService: UserService
) {

    @Transactional(readOnly = true)
    open fun authenticate(loginRequest: LoginRequestResource): OperationResult<AccessTokenResource> {
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

    @Transactional
    open fun refreshToken(refreshToken: String): OperationResult<RefreshTokenResource> {
        log.info("Generating new refresh token")
        val generatedResult = this.tokenGenerator.refreshToken(refreshToken)
        if (generatedResult.isFailure) {
            log.info("Could not generate refresh token")
            return OperationResult.failure(generatedResult.error, generatedResult.status)
        }

        return generatedResult
    }

    private fun generateToken(user: User, email: String): AccessTokenResource {
        val authentication = this.createAuthentication(user, email)
        return this.tokenGenerator.generateJwt(authentication)
    }

    private fun createAuthentication(user: User, email: String): Authentication {
        return Authentication.build(email, setOf(user.role.name))
    }

    companion object {
        @JvmStatic
        private val log = LoggerProvider.getLogger(AuthenticationService::class.java)
    }
}