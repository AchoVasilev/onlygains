package com.project.ports.rest

import com.project.application.models.category.CategoryViewResource
import com.project.application.services.CategoryService
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller(value = "/categories")
open class CategoryController(private val categoryService: CategoryService) {
    @Get
    open fun getCategories(): HttpResponse<List<CategoryViewResource>> {
        return HttpResponse.ok(categoryService.getCategories())
    }
}
