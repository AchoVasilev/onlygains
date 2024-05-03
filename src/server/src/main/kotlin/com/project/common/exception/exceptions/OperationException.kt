package com.project.common.exception.exceptions

import com.project.common.exception.base.BaseException
import com.project.common.exception.base.ErrorCode

data class OperationException(val error: ErrorCode = ErrorCode.OPERATION_RESULT_EXCEPTION) : BaseException(error)