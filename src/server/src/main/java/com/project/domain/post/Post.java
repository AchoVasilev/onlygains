package com.project.domain.post;

import com.project.domain.BaseEntity;
import com.project.domain.comment.Comment;
import com.project.domain.image.PostImage;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "posts")
public class Post extends BaseEntity {
    @Id
    private UUID id;
    private String title;
    private String text;
    @OneToMany
    private List<Comment> comments;
    @OneToMany
    private List<PostImage> postImages;
    private UUID categoryId;

    protected Post() {}

    public Post(String title, String text, UUID categoryId) {
        super();
        this.id = UUID.randomUUID();
        this.title = title;
        this.text = text;
        this.categoryId = categoryId;
        this.comments = new ArrayList<>();
        this.postImages = new ArrayList<>();
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public List<Comment> getComments() {
        return this.comments;
    }

    public UUID getCategoryId() {
        return categoryId;
    }
}
