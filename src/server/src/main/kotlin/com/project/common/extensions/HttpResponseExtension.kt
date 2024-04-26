package com.project.common.extensions

import com.project.common.BaseController
import com.project.common.result.OperationResult
import com.project.common.result.ResultStatus
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus

object HttpResponseExtension {
    fun <T> BaseController.toResponse(operationResult: OperationResult<T>): HttpResponse<T> {
        return when (operationResult.status) {
            ResultStatus.Ok -> HttpResponse.ok(operationResult.value)
            ResultStatus.NotFound -> HttpResponse.status(HttpStatus.NOT_FOUND, operationResult.errors.joinToString())
            ResultStatus.Error -> HttpResponse.status(HttpStatus.INTERNAL_SERVER_ERROR, operationResult.errors.joinToString())
            ResultStatus.Forbidden -> HttpResponse.status(HttpStatus.FORBIDDEN, operationResult.errors.joinToString())
            ResultStatus.Unauthorized -> HttpResponse.unauthorized()
            ResultStatus.Invalid -> HttpResponse.status(HttpStatus.BAD_REQUEST, operationResult.errors.joinToString())
            ResultStatus.Conflict -> HttpResponse.status(HttpStatus.CONFLICT, operationResult.errors.joinToString())
            ResultStatus.CriticalError -> HttpResponse.status(HttpStatus.INTERNAL_SERVER_ERROR, operationResult.errors.joinToString())
            ResultStatus.Unavailable -> HttpResponse.status(HttpStatus.SERVICE_UNAVAILABLE)
        }
    }
}