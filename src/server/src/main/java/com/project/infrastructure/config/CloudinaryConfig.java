package com.project.infrastructure.config;

import com.cloudinary.Cloudinary;
import io.github.cdimascio.dotenv.Dotenv;
import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;

@Factory
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {
        var dotEnv = Dotenv.configure().directory("misc/.env").load();
        var cloudinary = new Cloudinary(dotEnv.get("CLOUDINARY_URL"));
        cloudinary.config.secure = true;

        return cloudinary;
    }
}
