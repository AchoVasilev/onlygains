package com.project.ports.rest;

import com.project.application.models.category.CategoryViewResource;
import com.project.application.services.CategoryService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import java.util.List;

@Controller(value = "/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Get
    public HttpResponse<List<CategoryViewResource>> getCategories() {
        return HttpResponse.ok(this.categoryService.getCategories());
    }
}
