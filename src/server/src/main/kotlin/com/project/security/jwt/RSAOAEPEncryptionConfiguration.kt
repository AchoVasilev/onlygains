package com.project.security.jwt

import com.nimbusds.jose.EncryptionMethod
import com.nimbusds.jose.JWSAlgorithm
import io.micronaut.context.annotation.Value
import io.micronaut.core.io.Readable
import jakarta.inject.Singleton
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey

@Singleton
class RSAOAEPEncryptionConfiguration(
    @Value("\${jwt.private.key}") private val privateKey: Readable,
    @Value("\${jwt.public.key}") private val publicKey: Readable,
) {
    private val keyPair = KeyPairProvider.keyPair(privateKey, publicKey)
    var rsaPublicKey: RSAPublicKey = keyPair.public as RSAPublicKey
    val rsaPrivateKey: RSAPrivateKey = keyPair.private as RSAPrivateKey
    val jweAlgorithm: JWSAlgorithm = JWSAlgorithm.RS256
    val encryptionMethod: EncryptionMethod = EncryptionMethod.A128GCM
}