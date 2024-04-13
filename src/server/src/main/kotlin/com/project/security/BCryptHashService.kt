package com.project.security

import jakarta.inject.Singleton
import org.bouncycastle.crypto.generators.OpenBSDBCrypt
import java.nio.charset.StandardCharsets

@Singleton
class BCryptHashService : HashService {
    override fun hashText(text: String, salt: String): String {
        return OpenBSDBCrypt.generate(text.toByteArray(StandardCharsets.UTF_8), salt.toByteArray(), 5)
    }

    override fun matchHash(text: String, hash: String): Boolean {
        return OpenBSDBCrypt.checkPassword(hash, text.toByteArray(StandardCharsets.UTF_8))
    }
}