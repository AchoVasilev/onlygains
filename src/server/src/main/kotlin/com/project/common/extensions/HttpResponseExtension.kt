package com.project.common.extensions

import com.project.common.exception.exceptions.OperationException
import com.project.common.result.OperationResult
import com.project.common.result.ResultStatus
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus

fun OperationResult.toResponse(): HttpResponse<*> {
    return if (this is SuccessResult<*>) {
        HttpResponse.ok(this.value)
    } else {
        when ((this as FailureResult).status) {
            ResultStatus.NotFound -> HttpResponse.status<String>(HttpStatus.NOT_FOUND)
                .body(this.errors.joinToString())

            ResultStatus.Error -> HttpResponse.status<String>(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(this.errors.joinToString())

            ResultStatus.Forbidden -> HttpResponse.status<String>(HttpStatus.FORBIDDEN)
                .body(this.errors.joinToString())

            ResultStatus.Unauthorized -> HttpResponse.status<String>(HttpStatus.UNAUTHORIZED)
                .body(this.errors.joinToString())

            ResultStatus.Invalid -> HttpResponse.status<String>(HttpStatus.BAD_REQUEST)
                .body(this.errors.joinToString())

            ResultStatus.Conflict -> HttpResponse.status<String>(HttpStatus.CONFLICT)
                .body(this.errors.joinToString())

            else -> throw OperationException()
        }
    }
}
