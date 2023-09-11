package com.project.application.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.project.application.models.image.ImageResponseResource;
import com.project.infrastructure.exceptions.FileUploadException;
import io.micronaut.http.multipart.CompletedFileUpload;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Singleton
public class ImageService {
    private static final Logger log = LoggerFactory.getLogger(ImageService.class.getName());
    private final Cloudinary cloudinary;

    public ImageService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    public ImageResponseResource upload(String folder, CompletedFileUpload file) {
        final var rootFolderName = "onlygains";
        final var uploadFolder = String.format("%s/%s", rootFolderName, folder);
        try {
            var uploadedFile = this.convertMultipartToFile(file);
            var params = ObjectUtils.asMap(
                    "public_id", String.format("%s/%s", uploadFolder, uploadedFile.getName())
            );
            var uploadResult = this.cloudinary.uploader().upload(uploadedFile, params);
            var url = uploadResult.get("secure_url").toString();

            log.info("Successfully uploaded image. [name={}]", uploadedFile.getName());

            boolean isDeleted = uploadedFile.delete();
            if (isDeleted) {
                log.info("File successfully deleted");
            } else
                log.info("File doesn't exist");

            return new ImageResponseResource(url, file.getFilename());
        } catch (IOException e) {
            throw new FileUploadException(file.getClass(), e);
        }
    }

    private File convertMultipartToFile(CompletedFileUpload file) throws IOException {
        var convFile = new File(file.getFilename());
        var fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();

        return convFile;
    }
}
