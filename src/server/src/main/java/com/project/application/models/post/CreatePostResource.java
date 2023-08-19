package com.project.application.models.post;

import io.micronaut.serde.annotation.Serdeable;
import jakarta.annotation.Nonnull;

import java.util.List;
import java.util.UUID;

@Serdeable
public record CreatePostResource(@Nonnull String text, @Nonnull String title, List<String> imageUrls, UUID categoryId) {
}
