package com.project.common.exception.base

import io.micronaut.http.HttpStatus

enum class ErrorCode(val httpStatus: HttpStatus, val pattern: String) {
    AES_ENCRYPTION_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "AES encryption exception"),
    AES_DECRYPTION_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "AES decryption exception"),
    OPERATION_RESULT_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "Invalid status"),
    OPERATION_RESULT_VALUE_FAILED_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "The value of a failure result cannot be accessed"),
    OPERATION_RESULT_SHOULD_NOT_BE_SUCCESS_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "Cannot produce a failure result when operation is successful"),
    KEY_PAIR_EXTRACTION_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "PEM extraction exception");

    override fun toString(): String {
        return "${this.name}:${this.pattern}"
    }

    fun format(vararg args: String): String {
        if (args.isEmpty()) {
            return this.pattern
        }

        var formatted = this.pattern
        args.forEach { arg ->
            run {
                formatted = formatted.replaceFirst(Regex.escape("{}"), arg)
            }
        }

        return formatted
    }
}