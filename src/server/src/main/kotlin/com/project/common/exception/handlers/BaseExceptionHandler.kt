package com.project.common.exception.handlers

import com.project.common.exception.ErrorResponse
import com.project.common.exception.base.BaseException
import com.project.common.exception.base.ErrorCode
import io.micronaut.context.annotation.Requirements
import io.micronaut.context.annotation.Requires
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Produces
import io.micronaut.http.server.exceptions.ExceptionHandler

import jakarta.inject.Singleton

@Produces
@Singleton
@Requirements(
    Requires(classes = [BaseException::class, ExceptionHandler::class])
)
class BaseExceptionHandler : ExceptionHandler<BaseException, HttpResponse<*>>{
    override fun handle(request: HttpRequest<*>, exception: BaseException): HttpResponse<*> {
        return createErrorResponse(exception.errorCode, exception.message, exception.args)
    }

    private fun createErrorResponse(errorCode: ErrorCode, message: String?, args: List<String>): HttpResponse<ErrorResponse> {
        return HttpResponse.status<ErrorResponse?>(errorCode.httpStatus)
            .body(ErrorResponse(errorCode.name, errorCode.pattern, args, message))
    }
}