package com.project.infrastructure.exceptions.base

import io.micronaut.http.HttpStatus

enum class ErrorCode(val httpStatus: HttpStatus, val pattern: String) {
    AES_ENCRYPTION_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "AES encryption exception"),
    AES_DECRYPTION_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "AES decryption exception"),

    TOKEN_GENERATION_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "Token generation exception"),
    TOKEN_VERIFICATION_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "Token verification exception"),

    OPERATION_RESULT_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "Invalid status"),
    OPERATION_RESULT_VALUE_FAILED_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "Operation value exception"),
    OPERATION_RESULT_SHOULD_NOT_BE_SUCCESS_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "Operation success exception"),

    KEY_PAIR_EXTRACTION_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "PEM extraction exception"),

    INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");

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