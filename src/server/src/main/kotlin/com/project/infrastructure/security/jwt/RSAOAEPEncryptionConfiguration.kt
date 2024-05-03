package com.project.infrastructure.security.jwt

import com.nimbusds.jose.EncryptionMethod
import com.nimbusds.jose.JWEAlgorithm
import io.micronaut.context.annotation.Value
import io.micronaut.core.io.Readable
import io.micronaut.security.token.jwt.encryption.rsa.RSAEncryptionConfiguration
import jakarta.inject.Named
import jakarta.inject.Singleton
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey

@Named("generator")
@Singleton
class RSAOAEPEncryptionConfiguration(
    @Value("\${jwt.private.key}") private val privateKey: Readable,
    @Value("\${jwt.public.key}") private val publicKey: Readable,
) : RSAEncryptionConfiguration {
    private val keyPair = KeyPairProvider.keyPair(privateKey, publicKey)
    private var rsaPublicKey: RSAPublicKey = keyPair.public as RSAPublicKey
    private val rsaPrivateKey: RSAPrivateKey = keyPair.private as RSAPrivateKey
    private val jweAlgorithm: JWEAlgorithm = JWEAlgorithm.RSA_OAEP_256
    private val encryptionMethod: EncryptionMethod = EncryptionMethod.A256GCM

    override fun getPublicKey(): RSAPublicKey {
        return this.rsaPublicKey
    }

    override fun getPrivateKey(): RSAPrivateKey {
        return this.rsaPrivateKey
    }

    override fun getJweAlgorithm(): JWEAlgorithm {
        return this.jweAlgorithm
    }

    override fun getEncryptionMethod(): EncryptionMethod {
        return this.encryptionMethod
    }
}