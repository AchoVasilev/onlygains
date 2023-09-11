package com.project.application.models.category;

import com.project.domain.category.Category;
import io.micronaut.serde.annotation.Serdeable;

import java.util.UUID;

@Serdeable
public record CategoryViewResource(UUID id, String name, String translatedName, String imageUrl) {
    public static CategoryViewResource from(Category category) {
        return new CategoryViewResource(category.getId(), category.getName(), category.getTranslatedName(), category.getImageUrl());
    }
}
