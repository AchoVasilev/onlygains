package com.project.application.models.category;

import java.util.UUID;

public record CategoryViewResource(UUID id, String imageUrl, String name) {
}
