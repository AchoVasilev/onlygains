package com.project.security.jwt

import com.project.application.services.LoggerProvider
import io.micronaut.core.io.Readable
import org.bouncycastle.jce.provider.BouncyCastleProvider
import org.bouncycastle.openssl.PEMException
import org.bouncycastle.openssl.PEMKeyPair
import org.bouncycastle.openssl.PEMParser
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter
import org.slf4j.Logger
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStreamReader
import java.security.KeyPair
import java.security.Security

class KeyPairProvider {

    companion object {
        @JvmStatic
        fun keyPair(pemFile: Readable): KeyPair? {
            Security.addProvider(BouncyCastleProvider())

            val parser: PEMParser
            try {
                parser = PEMParser(InputStreamReader(pemFile.asInputStream()))
                val pemKeyPair = parser.readObject() as PEMKeyPair

                val converter = JcaPEMKeyConverter()
                val keyPair = converter.getKeyPair(pemKeyPair)
                parser.close()

                return keyPair
            } catch (ex: FileNotFoundException) {
                log.warn("file not found: {}", pemFile.name)
            } catch (ex: PEMException) {
                log.warn("PEM exception: {}", ex.message)
            } catch (ex: IOException) {
                log.warn("IO exception: {}", ex.message)
            }

            return null
        }

        @JvmStatic
        private val log: Logger = LoggerProvider.getLogger(KeyPairProvider::class.java)
    }
}