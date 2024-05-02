package com.project.security.jwt

import com.project.application.services.LoggerProvider
import com.project.common.exception.exceptions.KeyPairExtractionException
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


class KeyPairProvider {

    companion object {
        @JvmStatic
        fun keyPair(privateKey: Readable, publicKey: Readable): KeyPair {
            Security.addProvider(BouncyCastleProvider())

            return try {
                val publicKeyLoad = loadPublicKey(publicKey.asInputStream())
                val privateKeyLoad = loadPrivateKey(privateKey.asInputStream())

                KeyPair(publicKeyLoad, privateKeyLoad)
            } catch (ex: GeneralSecurityException) {
                log.warn("GeneralSecurityException exception: {}", ex.message)
                throw KeyPairExtractionException()
            } catch (ex: IOException) {
                log.warn("IO exception: {}", ex.message)
                throw KeyPairExtractionException()
            }
        }

        private fun loadPublicKey(publicKeyStream: InputStream): RSAPublicKey {
            publicKeyStream.use { `in` ->
                val pemParser =
                    PEMParser(InputStreamReader(`in`))
                val publicKeyInfo: SubjectPublicKeyInfo = SubjectPublicKeyInfo.getInstance(pemParser.readObject())
                val pubKeySpec =
                    X509EncodedKeySpec(publicKeyInfo.encoded)
                val keyFactory: KeyFactory = KeyFactory.getInstance("RSA")

                return keyFactory.generatePublic(pubKeySpec) as RSAPublicKey
            }
        }

        private fun loadPrivateKey(privateKeyStream: InputStream): RSAPrivateKey {
            privateKeyStream.use { `in` ->
                val pemParser =
                    PEMParser(InputStreamReader(`in`))
                val privateKeyInfo: PrivateKeyInfo = PrivateKeyInfo.getInstance(pemParser.readObject())
                val privateKeySpec =
                    PKCS8EncodedKeySpec(privateKeyInfo.encoded)
                val keyFactory: KeyFactory = KeyFactory.getInstance("RSA")

                return keyFactory.generatePrivate(privateKeySpec) as RSAPrivateKey
            }
        }

        @JvmStatic
        private val log: Logger = LoggerProvider.getLogger(KeyPairProvider::class.java)
    }
}