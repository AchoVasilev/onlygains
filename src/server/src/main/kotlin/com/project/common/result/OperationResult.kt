package com.project.common.result

open class OperationResult<T>(val value: T? = null) {

    private constructor(value: T, successMessage: String) : this(value) {
        this.successMessage = successMessage
    }

    private constructor(status: ResultStatus) : this() {
        this.status = status
    }

    private constructor(status: ResultStatus, errorMessages: List<String>) : this(status) {
        this.errors = errorMessages
    }

    var status: ResultStatus = ResultStatus.Ok
        private set

    val isSuccess: Boolean = this.status == ResultStatus.Ok
    var successMessage: String = String()
    var errors: List<String> = listOf()

    companion object {
        fun <T> success(value: T): OperationResult<T> {
            return OperationResult(value)
        }

        fun <T> success(value: T, successMessage: String): OperationResult<T> {
            return OperationResult(value, successMessage)
        }

        fun <T> notFound(): OperationResult<T> {
            return OperationResult(ResultStatus.NotFound)
        }

        fun <T> notFound(errors: List<String>): OperationResult<T> {
            return OperationResult(ResultStatus.NotFound, errors)
        }
    }
}