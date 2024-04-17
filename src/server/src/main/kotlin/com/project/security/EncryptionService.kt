package com.project.security

interface EncryptionService {
    fun encrypt(original: String, secret: String): String

    fun decrypt(encrypted: String, secret: String): String
}