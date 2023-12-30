package com.project.infrastructure.config

import com.cloudinary.Cloudinary
import io.github.cdimascio.dotenv.Dotenv
import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory

@Factory
class CloudinaryConfig {
    @Bean
    fun cloudinary(): Cloudinary {
        val dotEnv = Dotenv.configure().directory("./misc").filename(".env").load()
        val cloudinary = Cloudinary(dotEnv["CLOUDINARY_URL"])
        cloudinary.config.secure = true

        return cloudinary
    }
}
