package com.project.infrastructure.security.jwt

import com.project.infrastructure.exceptions.exceptions.KeyPairExtractionException
import com.project.infrastructure.utilities.LoggerProvider
import io.micronaut.core.io.Readable
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo
import org.bouncycastle.jce.provider.BouncyCastleProvider
import org.bouncycastle.openssl.PEMParser
import org.slf4j.Logger
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.security.GeneralSecurityException
import java.security.KeyFactory
import java.security.KeyPair
import java.security.Security
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec


object KeyPairProvider {

    @JvmStatic
    fun keyPair(privateKey: Readable, publicKey: Readable): KeyPair {
        Security.addProvider(BouncyCastleProvider())
        val keyFactory: KeyFactory = KeyFactory.getInstance("RSA")

        return try {
            val publicKeyLoad = loadPublicKey(publicKey.asInputStream(), keyFactory)
            val privateKeyLoad = loadPrivateKey(privateKey.asInputStream(), keyFactory)

            KeyPair(publicKeyLoad, privateKeyLoad)
        } catch (ex: GeneralSecurityException) {
            log.warn("GeneralSecurityException exception: {}", ex.message)
            throw KeyPairExtractionException()
        } catch (ex: IOException) {
            log.warn("IO exception: {}", ex.message)
            throw KeyPairExtractionException()
        }
    }

    private fun loadPublicKey(publicKeyStream: InputStream, keyFactory: KeyFactory): RSAPublicKey {
        publicKeyStream.use { inputStream ->
            val readObject = PEMParser(InputStreamReader(inputStream)).use { parser -> parser.readObject() }
            val publicKeyInfo: SubjectPublicKeyInfo = SubjectPublicKeyInfo.getInstance(readObject)
            val pubKeySpec = X509EncodedKeySpec(publicKeyInfo.encoded)

            return keyFactory.generatePublic(pubKeySpec) as RSAPublicKey
        }
    }

    private fun loadPrivateKey(privateKeyStream: InputStream, keyFactory: KeyFactory): RSAPrivateKey {
        privateKeyStream.use { inputStream ->
            val readObject = PEMParser(InputStreamReader(inputStream)).use { parser -> parser.readObject() }
            val privateKeyInfo: PrivateKeyInfo = PrivateKeyInfo.getInstance(readObject)
            val privateKeySpec = PKCS8EncodedKeySpec(privateKeyInfo.encoded)

            return keyFactory.generatePrivate(privateKeySpec) as RSAPrivateKey
        }
    }

    @JvmStatic
    private val log: Logger = LoggerProvider.getLogger(KeyPairProvider::class.java)
}