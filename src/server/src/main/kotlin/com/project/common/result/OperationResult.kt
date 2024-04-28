package com.project.common.result

import com.project.common.exception.base.ErrorCode
import com.project.common.exception.exceptions.OperationException

open class OperationResult private constructor(val isSuccess: Boolean) {

    protected constructor(isSuccess: Boolean, error: Error): this(isSuccess) {
        require(isSuccess && error != Error.none() || !isSuccess && error == Error.none()) {
            "Invalid error"
        }
    }

    val isFailure: Boolean
        get() = !isSuccess

    companion object {
        fun success(): OperationResult = OperationResult(true, Error.none())

        fun <TValue> success(value: TValue): ValueResult<TValue> = ValueResult(value, true, Error.none())

        fun failure(error: Error): OperationResult = OperationResult(false, error)

        fun <TValue> failureWithNull(error: Error): ValueResult<TValue> = ValueResult(null, false, error)
    }
}

class ValueResult<TValue>(private val value: TValue?, isSuccess: Boolean, error: Error) :
    OperationResult(isSuccess, error) {

    fun value(): TValue {
        return if (isSuccess) this.value!! else throw OperationException(ErrorCode.OPERATION_RESULT_VALUE_FAILED_EXCEPTION)
    }

    companion object {
        fun <TValue> fromNullable(value: TValue?): ValueResult<TValue> {
            return if (value != null) {
                success(value)
            } else {
                failureWithNull(Error.nullValue())
            }
        }
    }
}