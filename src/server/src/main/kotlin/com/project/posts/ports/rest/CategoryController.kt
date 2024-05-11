package com.project.posts.ports.rest

import com.project.posts.application.CategoryService
import com.project.posts.application.models.category.CategoryViewResource
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.security.annotation.Secured
import io.micronaut.security.rules.SecurityRule

@Controller(value = "/categories")
@Secured(SecurityRule.IS_ANONYMOUS)
open class CategoryController(private val categoryService: CategoryService) {
    @Get
    open fun getCategories(): HttpResponse<List<CategoryViewResource>> {
        return HttpResponse.ok(categoryService.getCategories())
    }
}
