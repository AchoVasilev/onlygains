package com.project.infrastructure.exceptions.exceptions

import com.project.infrastructure.exceptions.base.BaseException
import com.project.infrastructure.exceptions.base.ErrorCode

data class AesEncryptionException(val error: ErrorCode = ErrorCode.AES_ENCRYPTION_EXCEPTION) : BaseException(error)