package com.project.infrastructure.security.jwt

import com.nimbusds.jose.JWSHeader
import com.nimbusds.jose.crypto.RSASSASigner
import com.nimbusds.jose.crypto.RSASSAVerifier
import com.nimbusds.jwt.SignedJWT
import io.micronaut.context.annotation.Value
import io.micronaut.security.authentication.Authentication
import jakarta.inject.Singleton

@Singleton
class JwtService(
    private val encryptionConfiguration: RSASignatureConfiguration,
    private val jwtClaimsSetGenerator: JwtClaimsSetGenerator,
    @Value("\${jwt.expirationTimeInSeconds}") private val jwtExpirationInSeconds: Long,
) {
    private val signer: RSASSASigner = RSASSASigner(encryptionConfiguration.privateKey)

     fun generate(authentication: Authentication): String {
        RSASSAVerifier(this.encryptionConfiguration.publicKey)

        val claims = this.jwtClaimsSetGenerator.generateClaims(authentication, this.jwtExpirationInSeconds)

        val jwtHeader = JWSHeader.Builder(this.encryptionConfiguration.jwsAlgorithm)
            .build()

        val signedJwt = SignedJWT(jwtHeader, claims)
        signedJwt.sign(this.signer)

        return signedJwt.serialize()
    }
}