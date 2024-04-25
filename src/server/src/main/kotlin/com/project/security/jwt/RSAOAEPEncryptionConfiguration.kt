package com.project.security.jwt

import com.nimbusds.jose.EncryptionMethod
import com.nimbusds.jose.JWSAlgorithm
import io.micronaut.context.annotation.Value
import jakarta.inject.Singleton
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey

@Singleton
class RSAOAEPEncryptionConfiguration(@Value("\${pem.path}") pemPath: String) {
    val keyPair = KeyPairProvider.keyPair(pemPath)
    var rsaPublicKey: RSAPublicKey = keyPair?.public as RSAPublicKey
    val rsaPrivateKey: RSAPrivateKey = keyPair?.private as RSAPrivateKey
    val jweAlgorithm: JWSAlgorithm = JWSAlgorithm.RS256
    val encryptionMethod: EncryptionMethod = EncryptionMethod.A128GCM
}