package com.project.application.services

import com.cloudinary.Cloudinary
import com.cloudinary.utils.ObjectUtils
import com.project.application.models.image.ImageResponseResource
import com.project.infrastructure.exceptions.exceptions.FileUploadException
import io.micronaut.http.multipart.StreamingFileUpload
import jakarta.inject.Singleton
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.IOException

@Singleton
class ImageService(private val cloudinary: Cloudinary) {
    fun upload(folder: String?, file: StreamingFileUpload): ImageResponseResource {
        val rootFolderName = "myactivepal"
        val uploadFolder = String.format("%s/%s", rootFolderName, folder)
        val stream = file.asInputStream()
        try {
            val params = ObjectUtils.asMap(
                "public_id", String.format("%s/%s", uploadFolder, file.filename)
            )

            val uploadResult = cloudinary.uploader().uploadLarge(stream, params)
            val url = uploadResult["secure_url"].toString()

            log.info("Successfully uploaded image. [name={}]", file.filename)

            return ImageResponseResource(url, file.filename)
        } catch (e: IOException) {
            stream.close()
            throw FileUploadException(file.javaClass, e)
        }
    }

    companion object {
        private val log: Logger = LoggerFactory.getLogger(ImageService::class.java.name)
    }
}
