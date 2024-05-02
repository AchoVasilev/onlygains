package com.project.common.exception.exceptions

import com.project.common.exception.base.BaseException
import com.project.common.exception.base.ErrorCode
import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class KeyPairExtractionException(val code: ErrorCode = ErrorCode.KEY_PAIR_EXTRACTION_EXCEPTION) : BaseException(code)