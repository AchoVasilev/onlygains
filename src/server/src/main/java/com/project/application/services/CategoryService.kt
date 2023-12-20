package com.project.application.services

import com.project.application.models.category.CategoryViewResource
import com.project.domain.category.Category
import com.project.infrastructure.data.CategoryRepository
import io.micronaut.transaction.annotation.Transactional
import jakarta.inject.Singleton
import jakarta.persistence.EntityNotFoundException
import java.util.UUID

@Singleton
open class CategoryService(private val categoryRepository: CategoryRepository) {
    @Transactional(readOnly = true)
    open fun getCategories(): List<CategoryViewResource> {
        return categoryRepository.findAll()
                .map { category: Category -> CategoryViewResource.from(category) }
    }

    @Transactional(readOnly = true)
    open fun getCategoryBy(id: UUID): Category {
        return categoryRepository.findById(id)
                .orElseThrow { EntityNotFoundException(String.format("Category with %s does not exist", id)) }
    }
}
