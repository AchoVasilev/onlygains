package com.project.application.models.post;

import com.project.domain.post.Post;
import io.micronaut.serde.annotation.Serdeable;

import java.time.ZonedDateTime;
import java.util.UUID;

@Serdeable
public record PostViewResource(UUID id, String imageUrl, ZonedDateTime createdAt, String createdBy,
                               String creatorImageUrl, String title, String text, String previewText, String categoryName,
                               String categoryNameTranslation, UUID categoryId) {
    public static PostViewResource from(Post post) {
        return new PostViewResource(post.getId(),
                post.getPostImages().get(0).getUrl(),
                post.getCreatedAt(),
                post.getUser().getFullName(), post.getUser().getImageUrl(),
                post.getTitle(), post.getText(), post.getPreviewText(), post.getCategory().getName(),
                post.getCategory().getTranslatedName(), post.getCategory().getId());
    }
}
