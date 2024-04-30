package com.project.common.exception

import io.micronaut.http.HttpStatus
import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class HttpErrorResponse(
    val status: HttpStatus,
    val title: String,
    val description: String,
    val extensions: Map<String, Any>
) {
}