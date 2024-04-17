package com.project.common.exception.base

import io.micronaut.http.HttpStatus

enum class ErrorCode(private val httpStatus: HttpStatus, private val pattern: String) {
    AES_ENCRYPTION_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "AES encryption exception"),
    AES_DECRYPTION_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "AES decryption exception");

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