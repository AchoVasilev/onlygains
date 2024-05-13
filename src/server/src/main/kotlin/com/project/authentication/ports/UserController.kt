package com.project.authentication.ports

import com.project.authentication.UserService
import com.project.authentication.models.RegisterUserRequestResource
import com.project.common.result.OperationResult
import com.project.domain.user.User
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.security.annotation.Secured
import io.micronaut.security.rules.SecurityRule
import jakarta.validation.Valid
import java.net.URI

@Controller("/users")
@Secured(SecurityRule.IS_ANONYMOUS)
open class UserController(private val userService: UserService) {

    @Post
    open fun createUser(@Body @Valid request: RegisterUserRequestResource): HttpResponse<OperationResult<User>> {
        val result = this.userService.createUser(
            request.email,
            request.password,
            request.repeatPassword,
            request.firstName,
            request.lastName
        )

        return if (result.isSuccess)
            HttpResponse.created(result, URI.create("/users/${result.value()}}"))
        else
            return HttpResponse.badRequest(result)
    }
}