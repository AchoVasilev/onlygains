package com.project.domain.comment;

import com.project.domain.BaseEntity;
import com.project.domain.post.Post;
import com.project.domain.user.User;
import jakarta.persistence.CascadeType;
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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Like> likes;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Dislike> dislikes;

    private UUID parentId;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Comment> replies;

    protected Comment() {
        super();
        this.likes = new ArrayList<>();
        this.dislikes = new ArrayList<>();
        this.replies = new ArrayList<>();
    }

    public Comment(String text, Post post, User user) {
        this();
        this.id = UUID.randomUUID();
        this.text = text;
        this.post = post;
        this.user = user;
    }

    public UUID getId() {
        return this.id;
    }

    public String getText() {
        return this.text;
    }

    public Post getPost() {
        return this.post;
    }

    public User getUser() {
        return this.user;
    }

    public List<Like> getLikes() {
        return this.likes;
    }

    public List<Dislike> getDislikes() {
        return this.dislikes;
    }

    public UUID getParentId() {
        return this.parentId;
    }

    public List<Comment> getReplies() {
        return this.replies;
    }

    public void setParentId(UUID parentId) {
        this.parentId = parentId;
        this.setUpdatedAt();
    }

    public void reply(Comment comment) {
        comment.setParentId(this.id);
        this.replies.add(comment);
        this.setUpdatedAt();
    }
}
