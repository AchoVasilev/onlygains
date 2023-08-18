package com.project.application.models.category;

import io.micronaut.serde.annotation.Serdeable;

import java.util.UUID;
@Serdeable
public record CategoryViewResource(UUID id, String imageUrl, String name, String translatedName) {
}
