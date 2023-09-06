package com.project.application.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.project.infrastructure.exceptions.FileUploadException;
import io.micronaut.http.multipart.CompletedFileUpload;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@Singleton
public class ImageService {
    private static final Logger log = LoggerFactory.getLogger(ImageService.class.getName());
    private final Cloudinary cloudinary;

    public ImageService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    public String upload(String folder, CompletedFileUpload file) {
        try {
            var params = ObjectUtils.asMap(
                    "public_id", String.format("%s/%s", folder, file.getName())
            );
            var uploadResult = this.cloudinary.uploader().upload(file, params);
            var url = uploadResult.get("secure_url").toString();

            log.info("Successfully uploaded image. [name={}]", file.getName());

            return url;
        } catch (IOException e) {
            throw new FileUploadException(file.getClass(), e);
        }
    }
}
