package com.project.security.jwt

import com.nimbusds.jose.JWSAlgorithm
import io.micronaut.context.annotation.Value
import io.micronaut.core.io.Readable
import io.micronaut.security.token.jwt.signature.rsa.RSASignatureGeneratorConfiguration
import jakarta.inject.Named
import jakarta.inject.Singleton
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey

@Named("generator")
@Singleton
class RSASignatureConfiguration(
    @Value("\${jwt.private.key}") private val privateKey: Readable,
    @Value("\${jwt.public.key}") private val publicKey: Readable,
) : RSASignatureGeneratorConfiguration {

    private val keyPair = KeyPairProvider.keyPair(privateKey, publicKey)
    private var rsaPublicKey: RSAPublicKey = keyPair.public as RSAPublicKey
    private val rsaPrivateKey: RSAPrivateKey = keyPair.private as RSAPrivateKey
    private val jwsAlgorithm: JWSAlgorithm = JWSAlgorithm.RS256
    override fun getPublicKey(): RSAPublicKey {
        return this.rsaPublicKey
    }

    override fun getPrivateKey(): RSAPrivateKey {
        return this.rsaPrivateKey
    }

    override fun getJwsAlgorithm(): JWSAlgorithm {
        return this.jwsAlgorithm
    }
}