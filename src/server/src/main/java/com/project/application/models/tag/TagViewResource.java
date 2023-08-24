package com.project.application.models.tag;

import io.micronaut.serde.annotation.Serdeable;

import java.util.UUID;

@Serdeable
public record TagViewResource(UUID id, String name, String translatedName) {
}
