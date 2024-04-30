package com.project.common.extensions


import com.project.common.exception.HttpErrorResponse
import com.project.common.result.OperationResult
import com.project.common.result.ResultStatus
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus

fun HttpResponse<*>.fromResult(): HttpResponse<*> {
    if (this.body.isPresent && this.body.get() is OperationResult<*>) {
        val operationResult = this.body.get() as OperationResult<*>
        return when (operationResult.status) {
            ResultStatus.Ok -> HttpResponse.ok(operationResult.value())
            ResultStatus.Error -> HttpResponse.serverError(
                HttpErrorResponse(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "500 Internal server error",
                    "https://datatracker.ietf.org/doc/html/rfc7231#section-6.6.1",
                    mapOf(operationResult.error.code to operationResult.error.description)
                )
            )

            ResultStatus.Forbidden -> HttpResponse.status<HttpErrorResponse>(HttpStatus.FORBIDDEN)
                .body(
                    HttpErrorResponse(
                        HttpStatus.FORBIDDEN,
                        "403 Forbidden",
                        "https://datatracker.ietf.org/doc/html/rfc7231#section-6.5.3",
                        mapOf(operationResult.error.code to operationResult.error.description)
                    )
                )

            ResultStatus.Unauthorized -> HttpResponse.status<HttpErrorResponse>(HttpStatus.UNAUTHORIZED).body(
                HttpErrorResponse(
                    HttpStatus.UNAUTHORIZED,
                    "401 Unauthorized",
                    "https://datatracker.ietf.org/doc/html/rfc7235#section-3.1",
                    mapOf(operationResult.error.code to operationResult.error.description)
                )
            )

            ResultStatus.Invalid -> HttpResponse.badRequest(
                HttpErrorResponse(
                    HttpStatus.BAD_REQUEST,
                    "400 Bad Request",
                    "https://datatracker.ietf.org/doc/html/rfc7231#section-6.5.1",
                    mapOf(operationResult.error.code to operationResult.error.description)
                )
            )

            ResultStatus.NotFound -> HttpResponse.notFound(
                HttpErrorResponse(
                    HttpStatus.BAD_REQUEST,
                    "404 Not Found",
                    "https://datatracker.ietf.org/doc/html/rfc7231#section-6.5.4",
                    mapOf(operationResult.error.code to operationResult.error.description)
                )
            )

            ResultStatus.Conflict -> HttpResponse.status<HttpErrorResponse>(HttpStatus.CONFLICT).body(
                HttpErrorResponse(
                    HttpStatus.CONFLICT,
                    "409 Conflict",
                    "https://datatracker.ietf.org/doc/html/rfc7231#section-6.5.8",
                    mapOf(operationResult.error.code to operationResult.error.description)
                )
            )
        }
    }

    return this
}