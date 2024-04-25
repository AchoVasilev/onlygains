package com.project.security

import io.micronaut.context.annotation.Value
import jakarta.inject.Singleton

@Singleton
class EmailEncryptionService(
    private val encryptionService: EncryptionService,
    @Value("\${encryption.secret.email}") private val emailSecret: String
) {
    fun encryptEmail(email: String): String {
        return this.encryptionService.encrypt(email, this.emailSecret)
    }

    fun decryptEmail(email: String): String {
        return this.encryptionService.decrypt(email, this.emailSecret)
    }
}