package com.project.security.jwt

import com.nimbusds.jose.JWSHeader
import com.nimbusds.jose.crypto.RSASSASigner
import com.nimbusds.jose.crypto.RSASSAVerifier
import com.nimbusds.jwt.JWTClaimsSet
import com.nimbusds.jwt.SignedJWT
import io.micronaut.context.annotation.Value
import io.micronaut.security.authentication.Authentication
import jakarta.inject.Singleton
import java.time.Instant
import java.util.Date
import java.util.Objects

@Singleton
class JwtService(
    private val encryptionConfiguration: RSASignatureConfiguration,
    @Value("\${jwt.expirationTimeInSeconds}") private val jwtExpirationInSeconds: Long,
    @Value("\${jwt.issuer}") private val issuer: String
) {

    fun generate(authentication: Authentication): String {
        return this.generate(authentication, emptyMap())
    }

    private fun generate(authentication: Authentication, additionalClaims: Map<String, Objects>): String {
        val signer = RSASSASigner(this.encryptionConfiguration.privateKey)
        RSASSAVerifier(this.encryptionConfiguration.publicKey)

        val now = Instant.now()

        val claimsBuilder = JWTClaimsSet.Builder()
            .issuer(this.issuer)
            .issueTime(Date())
            .expirationTime(Date(now.plusSeconds(this.jwtExpirationInSeconds).toEpochMilli()))
            .subject(authentication.name)
            .claim("roles", authentication.roles)

        additionalClaims.forEach {claim -> claimsBuilder.claim(claim.key, claim.value)}

        val claims = claimsBuilder.build()

        val jwtHeader = JWSHeader.Builder(this.encryptionConfiguration.jwsAlgorithm)
            .build()

        val signedJwt = SignedJWT(jwtHeader, claims)
        signedJwt.sign(signer)

        return signedJwt.serialize()
    }
}