package com.project.common.exception.exceptions

import com.project.common.exception.base.BaseException
import com.project.common.exception.base.ErrorCode
import io.micronaut.serde.annotation.Serdeable

data class AesDecryptionException(val error: ErrorCode = ErrorCode.AES_DECRYPTION_EXCEPTION) : BaseException(error)