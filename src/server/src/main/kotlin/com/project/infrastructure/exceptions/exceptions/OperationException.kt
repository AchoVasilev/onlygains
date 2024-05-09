package com.project.infrastructure.exceptions.exceptions

import com.project.infrastructure.exceptions.base.BaseException
import com.project.infrastructure.exceptions.base.ErrorCode

data class OperationException(val error: ErrorCode = ErrorCode.OPERATION_RESULT_EXCEPTION) : BaseException(error)