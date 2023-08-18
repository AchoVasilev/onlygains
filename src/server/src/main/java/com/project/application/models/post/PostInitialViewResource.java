package com.project.application.models.post;

import java.util.UUID;

public record PostInitialViewResource(UUID id, String imageUrl, String createdAt, String createdBy, String title, String text) {
}
