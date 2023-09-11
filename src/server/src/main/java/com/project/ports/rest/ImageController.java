package com.project.ports.rest;

import com.project.application.models.image.ImageResponseResource;
import com.project.application.services.ImageService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.multipart.CompletedFileUpload;

import static io.micronaut.http.MediaType.MULTIPART_FORM_DATA;

@Controller("/images")
public class ImageController {
    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @Post(value = "/{folder}", consumes = {MULTIPART_FORM_DATA})
    public HttpResponse<ImageResponseResource> upload(@PathVariable String folder, CompletedFileUpload file) {
        return HttpResponse.ok(this.imageService.upload(folder, file));
    }
}
