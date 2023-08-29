package com.project.application.services;

import com.project.application.models.category.CategoryViewResource;
import com.project.domain.category.Category;
import com.project.infrastructure.data.CategoryRepository;
import io.micronaut.transaction.annotation.Transactional;
import jakarta.inject.Singleton;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.UUID;

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
                .map(CategoryViewResource::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public Category getCategoryBy(UUID id) {
        return this.categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Category with %s does not exist", id)));
    }
}
