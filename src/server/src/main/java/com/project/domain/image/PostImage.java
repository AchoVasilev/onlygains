package com.project.domain.image;

import com.project.domain.post.Post;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "post_images")
public class PostImage extends Image {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    protected PostImage() {
    }

    public PostImage(String url, Post post) {
        super(url);
        this.post = post;
    }

    public Post getPost() {
        return post;
    }
}
