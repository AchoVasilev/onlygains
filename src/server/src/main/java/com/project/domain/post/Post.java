package com.project.domain.post;

import com.project.domain.BaseEntity;
import com.project.domain.comment.Comment;
import com.project.domain.image.PostImage;
import com.project.domain.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
    @ManyToOne
    private User user;

    protected Post() {}

    public Post(String title, String text, UUID categoryId, List<PostImage> postImages, User user) {
        super();
        this.id = UUID.randomUUID();
        this.title = title;
        this.text = text;
        this.categoryId = categoryId;
        this.comments = new ArrayList<>();
        this.postImages = postImages;
        this.user = user;
    }

    public UUID getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getText() {
        return this.text;
    }

    public List<Comment> getComments() {
        return this.comments;
    }

    public UUID getCategoryId() {
        return this.categoryId;
    }

    public List<PostImage> getPostImages() {
        return this.postImages;
    }

    public User getUser() {
        return this.user;
    }
}
