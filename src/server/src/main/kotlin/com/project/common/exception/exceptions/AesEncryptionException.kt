package com.project.common.exception.exceptions

import com.project.common.exception.base.BaseException
import com.project.common.exception.base.ErrorCode
import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class AesEncryptionException(val error: ErrorCode = ErrorCode.AES_ENCRYPTION_EXCEPTION) : BaseException(error)