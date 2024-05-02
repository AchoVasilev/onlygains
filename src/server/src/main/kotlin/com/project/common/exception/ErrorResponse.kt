package com.project.common.exception

import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class ErrorResponse(val code: String, val pattern: String?, val args: List<String>, val message: String?)
