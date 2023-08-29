package com.project.application.models.post;

import com.project.application.models.category.CategoryViewResource;
import com.project.domain.image.Image;
import com.project.domain.post.Post;
import io.micronaut.serde.annotation.Serdeable;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Serdeable
public record PostDetailsResource(UUID id, ZonedDateTime createdAt, String createdBy, String title,
                                  String text, List<String> imageUrls, CategoryViewResource category,
                                  List<CommentViewResource> comments) {
    public static PostDetailsResource from(Post post) {
        return new PostDetailsResource(post.getId(), post.getCreatedAt(), post.getUser().getFullName(), post.getTitle(),
                post.getText(), post.getPostImages().stream().map(Image::getUrl).toList(),
                CategoryViewResource.from(post.getCategory()),
                post.getComments().stream().map(CommentViewResource::from).toList());
    }
}
