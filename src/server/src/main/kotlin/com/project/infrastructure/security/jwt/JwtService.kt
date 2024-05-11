package com.project.infrastructure.security.jwt

import com.nimbusds.jose.JWSHeader
import com.nimbusds.jose.crypto.RSASSASigner
import com.nimbusds.jose.crypto.RSASSAVerifier
import com.nimbusds.jwt.SignedJWT
import com.project.infrastructure.utilities.LoggerProvider
import io.micronaut.context.annotation.Value
import io.micronaut.security.authentication.Authentication
import jakarta.inject.Singleton

@Singleton
class JwtService(
    private val encryptionConfiguration: RSASignatureConfiguration,
    private val jwtClaimsSetGenerator: JwtClaimsSetGenerator,
    @Value("\${jwt.expirationTimeInSeconds}") private val jwtExpirationInSeconds: Int,
) {
    private val signer: RSASSASigner = RSASSASigner(encryptionConfiguration.privateKey)

    fun generate(authentication: Authentication): String {
        log.info("Generating JWT for user. [email={}]", authentication.name)

        RSASSAVerifier(this.encryptionConfiguration.publicKey)

        val claims = this.jwtClaimsSetGenerator.generateClaims(authentication, this.jwtExpirationInSeconds.toLong())

        val jwtHeader = JWSHeader.Builder(this.encryptionConfiguration.jwsAlgorithm)
            .build()

        val signedJwt = SignedJWT(jwtHeader, claims)
        signedJwt.sign(this.signer)
        log.info("JWT signed")

        return signedJwt.serialize()
    }

    fun getExpiration(): Int {
        return this.jwtExpirationInSeconds
    }

    companion object {
        @JvmStatic
        private val log = LoggerProvider.getLogger(JwtService::class.java)
    }
}