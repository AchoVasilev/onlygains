package com.project.posts.ports.rest

import com.project.posts.application.models.tag.TagViewResource
import com.project.posts.application.TagService
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller(value = "/tags")
open class TagController(private val tagService: TagService) {
    @Get
    open fun getTags(): HttpResponse<List<TagViewResource>> {
        return HttpResponse.ok(tagService.getTags())
    }
}
