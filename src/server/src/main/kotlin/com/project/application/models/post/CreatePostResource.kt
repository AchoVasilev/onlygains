package com.project.application.models.post

import io.micronaut.serde.annotation.Serdeable
import jakarta.annotation.Nonnull
import java.util.UUID

@Serdeable
data class CreatePostResource(@Nonnull val text: String,
                         @Nonnull val title: String,
                         val previewText: String,
                         val imageUrls: List<String>,
                         val categoryId: UUID,
                         val tags: List<UUID>) {
}
