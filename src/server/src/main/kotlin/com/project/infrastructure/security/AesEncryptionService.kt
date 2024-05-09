package com.project.infrastructure.security

import com.project.infrastructure.utilities.LoggerProvider
import com.project.infrastructure.exceptions.exceptions.AesDecryptionException
import com.project.infrastructure.exceptions.exceptions.AesEncryptionException
import com.project.posts.application.PostService
import io.micronaut.context.annotation.Value
import jakarta.inject.Singleton
import org.slf4j.Logger
import java.nio.charset.StandardCharsets
import java.util.Base64
import javax.crypto.Cipher
import javax.crypto.spec.GCMParameterSpec
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

@Singleton
class AesEncryptionService : EncryptionService {
    private val algorithm = "AES"
    private val aesTransformation = "AES/GCM/NoPadding"
    private val gcmTagLength = 16

    @Value("\${encryption.initializationVector}")
    private var encryptionInitVector = ""

    override fun encrypt(original: String, secret: String): String {
        log.info("Encrypting $original")
        if (original.isBlank()) {
            return ""
        }

        try {
            val ivParamSpec = IvParameterSpec(this.encryptionInitVector.toByteArray(StandardCharsets.UTF_8))
            val keySpec = SecretKeySpec(Base64.getDecoder().decode(secret), this.algorithm)
            val gcmParameterSpec = GCMParameterSpec(this.gcmTagLength * 8, ivParamSpec.iv)

            val cipher = Cipher.getInstance(this.aesTransformation)

            cipher.init(Cipher.ENCRYPT_MODE, keySpec, gcmParameterSpec)

            val cipherText = cipher.doFinal(original.toByteArray(StandardCharsets.UTF_8))

            return Base64.getEncoder().encodeToString(cipherText)
        } catch (e: Exception) {
            log.error("Encryption problem", e)
            throw AesEncryptionException()
        }
    }

    override fun decrypt(encrypted: String, secret: String): String {
        if (encrypted.isBlank()) {
            return ""
        }

        try {
            val ivParamSpec = IvParameterSpec(this.encryptionInitVector.toByteArray(StandardCharsets.UTF_8))
            val keySpec = SecretKeySpec(Base64.getDecoder().decode(secret), this.algorithm)
            val gcmParameterSpec = GCMParameterSpec(this.gcmTagLength * 8, ivParamSpec.iv)

            val cipher = Cipher.getInstance(this.aesTransformation)

            cipher.init(Cipher.DECRYPT_MODE, keySpec, gcmParameterSpec)

            val cipherText = cipher.doFinal(Base64.getDecoder().decode(encrypted))

            return String(cipherText)
        } catch (e: Exception) {
            log.error("Decryption problem", e)
            throw AesDecryptionException()
        }
    }

    companion object {
        @JvmStatic
        private val log: Logger = LoggerProvider.getLogger(PostService::class.java)
    }
}