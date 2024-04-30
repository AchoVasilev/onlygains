package com.project.common.exception

data class ErrorResponse(val code: String, val pattern: String?, val args: List<String>, val message: String?)
