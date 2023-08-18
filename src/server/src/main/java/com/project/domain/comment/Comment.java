package com.project.domain.comment;

import com.project.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "comments")
public class Comment extends BaseEntity {
    @Id
    private UUID id;
    private String text;
    @Column(name = "post_id")
    private UUID postId;

    @OneToMany
    private List<Like> likes;
    @OneToMany
    private List<Dislike> dislikes;

    protected Comment() {
    }

    public Comment(String text, UUID postId) {
        super();
        this.id = UUID.randomUUID();
        this.text = text;
        this.postId = postId;
        this.likes = new ArrayList<>();
        this.dislikes = new ArrayList<>();
    }

    public UUID getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public UUID getPostId() {
        return postId;
    }

    public List<Like> getLikes() {
        return likes;
    }

    public List<Dislike> getDislikes() {
        return dislikes;
    }
}
