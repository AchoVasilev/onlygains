package com.project.common.exception.exceptions

import com.project.common.exception.base.BaseException
import com.project.common.exception.base.ErrorCode

class OperationException(errorCode: ErrorCode = ErrorCode.OPERATION_RESULT_EXCEPTION) : BaseException(errorCode)