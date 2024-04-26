package com.project.authentication.ports

import com.project.application.models.user.UserViewResource
import com.project.authentication.UserService
import com.project.authentication.models.RegisterUserRequestResource
import com.project.common.BaseController
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.security.annotation.Secured
import io.micronaut.security.rules.SecurityRule
import jakarta.validation.Valid

@Controller("/users")
@Secured(SecurityRule.IS_ANONYMOUS)
open class UserController(private val userService: UserService) : BaseController() {

    @Post
    open fun createUser(@Body @Valid request: RegisterUserRequestResource): HttpResponse<UserViewResource> {
        val result = this.userService.createUser(request.email, request.password, request.firstName, request.lastName)

        return if (result.isSuccess) HttpResponse.created(UserViewResource.from(result.value!!))
        else HttpResponse.status(HttpStatus.BAD_REQUEST).
    }
}