package com.project.domain.comment;

import com.project.domain.BaseEntity;
import com.project.domain.post.Post;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "comments")
public class Comment extends BaseEntity {
    @Id
    private UUID id;
    private String text;
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @OneToMany
    private List<Like> likes;
    @OneToMany
    private List<Dislike> dislikes;

    protected Comment() {
    }

    public Comment(String text, Post post) {
        super();
        this.id = UUID.randomUUID();
        this.text = text;
        this.post = post;
        this.likes = new ArrayList<>();
        this.dislikes = new ArrayList<>();
    }

    public UUID getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Post getPost() {
        return post;
    }

    public List<Like> getLikes() {
        return likes;
    }

    public List<Dislike> getDislikes() {
        return dislikes;
    }
}
