package com.project.authentication

import com.project.application.services.LoggerProvider
import com.project.common.result.OperationResult
import com.project.domain.user.User
import com.project.infrastructure.data.UserRepository
import com.project.security.EmailEncryptionService
import com.project.security.HashService
import com.project.utilities.Time.utcNow
import com.project.utilities.Time.utcZone
import io.micronaut.transaction.annotation.Transactional
import jakarta.inject.Singleton
import java.nio.charset.StandardCharsets
import java.time.Instant
import java.time.LocalDate
import java.util.Base64

@Singleton
open class UserService(
    private val userRepository: UserRepository,
    private val emailEncryptionService: EmailEncryptionService,
    private val hashService: HashService
) {

    @Transactional
    open fun createUser(email: String, password: String, firstName: String, lastName: String): OperationResult<User> {
        val user = this.findUserBy(email)
        if (user != null) {
            log.info("User exists. [email={}]", email)
            return OperationResult.badRequest(listOf("User exists!"))
        }

        val now = utcNow()
        val hashedPassword = this.hashPassword(password, now)
    }

    @Transactional(readOnly = true)
    open fun findUserBy(email: String, password: String): OperationResult<User> {
        val user = this.findUserBy(email)

        if (user == null) {
            log.info("User not found. [email={}]", email)
            return OperationResult.notFound(listOf("Credentials do not match!"))
        }
        val passwordsMatch = this.hashService.matchHash(password, user.password)
        if (!passwordsMatch) {
            log.info("User credentials do not match. [email={}]", email)
            return OperationResult.badRequest(listOf("Credentials do not match!"))
        }

        return OperationResult.success(user)
    }

    private fun findUserBy(email: String): User? {
        val encryptedMail = this.emailEncryptionService.encryptEmail(email)
        return this.userRepository.findByEmail(encryptedMail)
    }

    private fun hashPassword(password: String, createdAt: Instant): String {
        return this.hashService.hashText(password, this.createSalt(createdAt))
    }

    private fun createSalt(createdAt: Instant): String {
        val dateNow = LocalDate.now()
        val epochMillis = createdAt.toEpochMilli()
        val salt = createdAt.plusNanos(dateNow.plusYears(epochMillis).atStartOfDay(utcZone).toEpochSecond())
            .minusMillis(dateNow.plusDays(epochMillis).atStartOfDay(utcZone).toEpochSecond())
            .plusSeconds(dateNow.plusWeeks(createdAt.toEpochMilli()).atStartOfDay(utcZone).toEpochSecond())

        return String(Base64.getEncoder().encode(salt.toString().toByteArray(StandardCharsets.UTF_8)))
    }

    companion object {
        @JvmStatic
        private val log = LoggerProvider.getLogger(Companion::class.java)
    }
}