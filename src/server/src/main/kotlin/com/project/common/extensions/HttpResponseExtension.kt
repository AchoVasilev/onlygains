package com.project.common.extensions

import com.project.common.BaseController
import com.project.common.result.OperationResult
import com.project.common.result.ResultStatus
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus

object HttpResponseExtension {
    fun <T> BaseController.toResponse(operationResult: OperationResult<T>): HttpResponse<Any> {
        return when (operationResult.status) {
            ResultStatus.Ok -> HttpResponse.ok(operationResult.value)
            ResultStatus.NotFound -> HttpResponse.notFound(operationResult.errors)
            ResultStatus.Error -> HttpResponse.serverError(operationResult.errors)
            ResultStatus.Forbidden -> HttpResponse.status(HttpStatus.FORBIDDEN, operationResult.errors.firstOrNull())
            ResultStatus.Unauthorized -> HttpResponse.unauthorized()
            ResultStatus.Invalid -> HttpResponse.badRequest(operationResult.errors)
            ResultStatus.Conflict -> HttpResponse.status(HttpStatus.CONFLICT, operationResult.errors.firstOrNull())
            ResultStatus.CriticalError -> HttpResponse.serverError(operationResult.errors)
            ResultStatus.Unavailable -> HttpResponse.status(HttpStatus.SERVICE_UNAVAILABLE)
        }
    }
}