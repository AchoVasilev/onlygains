package com.project.common.result

import io.micronaut.http.HttpStatus

enum class ResultStatus {
    Ok,
    Error,
    Forbidden,
    Unauthorized,
    Invalid,
    NotFound,
    Conflict;

    fun toHttpStatus(): HttpStatus {
        return when(this) {
            Ok -> HttpStatus.OK
            Error -> HttpStatus.INTERNAL_SERVER_ERROR
            Forbidden -> HttpStatus.FORBIDDEN
            Unauthorized -> HttpStatus.UNAUTHORIZED
            Invalid -> HttpStatus.BAD_REQUEST
            NotFound -> HttpStatus.NOT_FOUND
            Conflict -> HttpStatus.CONFLICT
        }
    }
}