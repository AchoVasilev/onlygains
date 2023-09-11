package com.project.application.models.tag;

import com.project.domain.post.Tag;
import io.micronaut.serde.annotation.Serdeable;

import java.util.UUID;

@Serdeable
public record TagViewResource(UUID id, String name, String translatedName) {
    public static TagViewResource from (Tag tag) {
        return new TagViewResource(tag.getId(), tag.getName(), tag.getTranslatedName());
    }
}
