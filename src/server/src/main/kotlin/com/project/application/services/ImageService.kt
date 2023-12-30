package com.project.application.services

import com.cloudinary.Cloudinary
import com.cloudinary.utils.ObjectUtils
import com.project.application.models.image.ImageResponseResource
import com.project.infrastructure.exceptions.FileUploadException
import io.micronaut.http.multipart.CompletedFileUpload
import jakarta.inject.Singleton
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

@Singleton
class ImageService(private val cloudinary: Cloudinary) {
    fun upload(folder: String?, file: CompletedFileUpload): ImageResponseResource {
        val rootFolderName = "myactivepal"
        val uploadFolder = String.format("%s/%s", rootFolderName, folder)
        try {
            val uploadedFile = this.convertMultipartToFile(file)
            val params = ObjectUtils.asMap(
                    "public_id", String.format("%s/%s", uploadFolder, uploadedFile.name)
            )
            val uploadResult = cloudinary.uploader().upload(uploadedFile, params)
            val url = uploadResult["secure_url"].toString()

            log.info("Successfully uploaded image. [name={}]", uploadedFile.name)

            val isDeleted = uploadedFile.delete()
            if (isDeleted) {
                log.info("File successfully deleted")
            } else {
                log.info("File doesn't exist")
            }

            return ImageResponseResource(url, file.filename)
        } catch (e: IOException) {
            throw FileUploadException(file.javaClass, e)
        }
    }

    @Throws(IOException::class)
    private fun convertMultipartToFile(file: CompletedFileUpload): File {
        val convFile = File(file.filename)
        val fos = FileOutputStream(convFile)
        fos.write(file.bytes)
        fos.close()

        return convFile
    }

    companion object {
        private val log: Logger = LoggerFactory.getLogger(ImageService::class.java.name)
    }
}
