package com.project.posts.ports.rest

import com.project.posts.application.TagService
import com.project.posts.application.models.tag.TagViewResource
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.security.annotation.Secured
import io.micronaut.security.rules.SecurityRule

@Controller(value = "/tags")
@Secured(SecurityRule.IS_ANONYMOUS)
open class TagController(private val tagService: TagService) {
    @Get
    open fun getTags(): HttpResponse<List<TagViewResource>> {
        return HttpResponse.ok(tagService.getTags())
    }
}
