package com.project.ports.rest

import com.project.application.models.image.ImageResponseResource
import com.project.application.services.ImageService
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Post
import io.micronaut.http.multipart.CompletedFileUpload

@Controller("/images")
open class ImageController(private val imageService: ImageService) {
    @Post(value = "/{folder}", consumes = [MediaType.MULTIPART_FORM_DATA])
    open fun upload(@PathVariable folder: String?, file: CompletedFileUpload): HttpResponse<ImageResponseResource> {
        return HttpResponse.ok(imageService.upload(folder, file))
    }
}
