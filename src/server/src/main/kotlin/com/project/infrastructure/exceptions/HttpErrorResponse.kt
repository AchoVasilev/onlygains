package com.project.infrastructure.exceptions

import io.micronaut.http.HttpStatus
import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class HttpErrorResponse(
    val status: HttpStatus,
    val title: String,
    val description: String,
    val extensions: Map<String, Any>?
) {
    companion object {
        @JvmStatic
        fun toNotFound(extensions: Map<String, Any>?): HttpErrorResponse {
            return HttpErrorResponse(
                HttpStatus.NOT_FOUND,
                "404 Not Found",
                "https://datatracker.ietf.org/doc/html/rfc7231#section-6.5.4",
                extensions
            )
        }

        @JvmStatic
        fun toBadRequest(extensions: Map<String, Any>?): HttpErrorResponse {
            return HttpErrorResponse(
                HttpStatus.BAD_REQUEST,
                "400 Bad Request",
                "https://datatracker.ietf.org/doc/html/rfc7231#section-6.5.1",
                extensions
            )
        }
    }
}