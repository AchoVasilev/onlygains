package com.project.authentication

import com.project.application.services.LoggerProvider
import com.project.common.result.OperationResult
import com.project.domain.user.Role
import com.project.domain.user.User
import com.project.infrastructure.data.UserRepository
import com.project.security.EmailEncryptionService
import com.project.security.HashService
import io.micronaut.transaction.annotation.Transactional
import jakarta.inject.Singleton
import java.security.SecureRandom
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

        val hashedPassword = this.hashPassword(password)
        val hashedEmail = this.emailEncryptionService.encryptEmail(email)
        val newUser = User(hashedEmail, hashedPassword, firstName, lastName, Role.USER)

        val result = OperationResult.success(this.userRepository.save(newUser))
        log.info("Successfully created user. [id={}, email={}]", newUser.id, email)

        return result
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

    @Transactional(readOnly = true)
    open fun findUserBy(email: String): User? {
        val encryptedMail = this.emailEncryptionService.encryptEmail(email)
        return this.userRepository.findByEmail(encryptedMail)
    }

    private fun hashPassword(password: String): String {
        return this.hashService.hashText(password, this.createSalt())
    }

    private fun createSalt(): String {
        val random = SecureRandom()
        val bytes = ByteArray(10)
        random.nextBytes(bytes)

        return String(Base64.getEncoder().encode(bytes))
    }

    companion object {
        @JvmStatic
        private val log = LoggerProvider.getLogger(Companion::class.java)
    }
}