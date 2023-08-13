package com.project.domain.image;

import jakarta.persistence.Entity;

@Entity(name = "post_images")
public class PostImage extends Image {
    protected PostImage() {
    }

    public PostImage(String url) {
        super(url);
    }
}
