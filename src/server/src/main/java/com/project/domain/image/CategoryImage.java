package com.project.domain.image;

import jakarta.persistence.Entity;

@Entity(name = "category_images")
public class CategoryImage extends Image {
    protected CategoryImage() {
    }

    public CategoryImage(String url) {
        super(url);
    }
}
