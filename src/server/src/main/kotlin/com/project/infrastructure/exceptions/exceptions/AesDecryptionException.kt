package com.project.infrastructure.exceptions.exceptions

import com.project.infrastructure.exceptions.base.BaseException
import com.project.infrastructure.exceptions.base.ErrorCode

data class AesDecryptionException(val error: ErrorCode = ErrorCode.AES_DECRYPTION_EXCEPTION) : BaseException(error)