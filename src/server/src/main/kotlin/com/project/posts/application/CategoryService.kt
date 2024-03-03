package com.project.posts.application

import com.project.posts.application.models.category.CategoryViewResource
import com.project.posts.domain.Category
import com.project.posts.domain.CategoryRepository
import io.micronaut.transaction.annotation.Transactional
import jakarta.inject.Singleton
import jakarta.persistence.EntityNotFoundException
import java.util.UUID

@Singleton
open class CategoryService(private val categoryRepository: CategoryRepository) {
    @Transactional(readOnly = true)
    open fun getCategories(): List<CategoryViewResource> {
        val cats = this.categoryRepository.findAll()
        return categoryRepository.findAll()
                .map { category: Category -> CategoryViewResource.from(category) }
    }

    @Transactional(readOnly = true)
    open fun getCategoryBy(id: UUID): Category {
        return categoryRepository.findById(id)
                .orElseThrow { EntityNotFoundException(String.format("Category with %s does not exist", id)) }
    }
}
