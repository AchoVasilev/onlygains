package com.project.domain.image;

import jakarta.persistence.Entity;

@Entity(name = "user_images")
public class UserImage extends Image {
    protected UserImage() {}

    public UserImage(String url) {
        super(url);
    }
}
