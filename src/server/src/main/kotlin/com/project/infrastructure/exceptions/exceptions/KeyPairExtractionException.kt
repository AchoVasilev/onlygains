package com.project.infrastructure.exceptions.exceptions

import com.project.infrastructure.exceptions.base.BaseException
import com.project.infrastructure.exceptions.base.ErrorCode

data class KeyPairExtractionException(val code: ErrorCode = ErrorCode.KEY_PAIR_EXTRACTION_EXCEPTION) : BaseException(code)