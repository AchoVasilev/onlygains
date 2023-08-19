package com.project.application.models.post;

import io.micronaut.serde.annotation.Serdeable;

import java.util.UUID;

@Serdeable
public record PostInitialViewResource(UUID id, String imageUrl, String createdAt, String createdBy, String title, String text) {
}
