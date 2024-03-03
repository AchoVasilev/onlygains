package com.project.posts.application.models.category

import com.project.posts.domain.Category
import io.micronaut.serde.annotation.Serdeable
import java.util.UUID

@Serdeable
data class CategoryViewResource(val id: UUID, val name: String?, val translatedName: String?, val imageUrl: String?) {
    companion object {
        @JvmStatic
        fun from(category: Category): CategoryViewResource {
            return CategoryViewResource(category.id, category.name, category.translatedName, category.imageUrl)
        }
    }
}
