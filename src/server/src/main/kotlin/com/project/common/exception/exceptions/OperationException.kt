package com.project.common.exception.exceptions

import com.project.common.exception.base.BaseException
import com.project.common.exception.base.ErrorCode
import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class OperationException(val error: ErrorCode = ErrorCode.OPERATION_RESULT_EXCEPTION) : BaseException(error)