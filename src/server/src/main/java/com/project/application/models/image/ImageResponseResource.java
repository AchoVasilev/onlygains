package com.project.application.models.image;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record ImageResponseResource(String url, String name) {
}
