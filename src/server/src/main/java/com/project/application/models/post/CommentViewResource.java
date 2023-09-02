package com.project.application.models.post;

import com.project.application.models.user.UserViewResource;
import com.project.domain.comment.Comment;
import io.micronaut.serde.annotation.Serdeable;

import java.time.ZonedDateTime;
import java.util.UUID;

@Serdeable
public record CommentViewResource(UUID id, UserViewResource createdBy, ZonedDateTime createdAt, String text) {
    public static CommentViewResource from(Comment comment) {
        return new CommentViewResource(comment.getId(), UserViewResource.from(comment.getUser()), comment.getCreatedAt(), comment.getText());
    }
}
