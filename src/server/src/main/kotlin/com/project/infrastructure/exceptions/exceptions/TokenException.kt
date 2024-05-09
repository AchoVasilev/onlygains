package com.project.infrastructure.exceptions.exceptions

import com.project.infrastructure.exceptions.base.BaseException
import com.project.infrastructure.exceptions.base.ErrorCode

data class TokenException(val code: ErrorCode = ErrorCode.TOKEN_GENERATION_EXCEPTION) : BaseException(code)