package com.project.application.services;

import com.project.application.models.category.CategoryViewResource;
import com.project.infrastructure.data.CategoryRepository;
import io.micronaut.transaction.annotation.Transactional;
import jakarta.inject.Singleton;

import java.util.List;

@Singleton
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional(readOnly = true)
    public List<CategoryViewResource> getCategories() {
        return this.categoryRepository.findAll()
                .stream()
                .map(c -> new CategoryViewResource(c.getId(), c.getImageUrl(), c.getName(), c.getTranslatedName()))
                .toList();
    }
}