package com.project.infrastructure.security.refresh

import com.nimbusds.jose.JWSAlgorithm
import io.micronaut.context.annotation.Value
import io.micronaut.security.token.jwt.generator.RefreshTokenConfiguration
import jakarta.inject.Singleton

@Singleton
class RefreshTokenConfiguration(
    @Value("jwt.refresh-token.secret") private val secret: String,
    @Value("jwt.refresh-token.expirationTimeInSeconds") private val expirationTimeInSeconds: Long,
    @Value("jwt.refresh-toke.base64") private val isEncoded: Boolean,
    @Value("jwt-refresh-token.jws-algorithm") private val jwsAlgorithm: JWSAlgorithm
) : RefreshTokenConfiguration {

    override fun getJwsAlgorithm(): JWSAlgorithm {
        return this.jwsAlgorithm
    }

    override fun getSecret(): String {
        return this.secret
    }

    override fun isBase64(): Boolean {
        return this.isEncoded
    }

    fun getExpirationTime(): Long {
        return this.expirationTimeInSeconds
    }
}