package com.project.domain.category;

import com.project.domain.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity(name = "categories")
public class Category extends BaseEntity {
    @Id
    private UUID id;
    private String name;
    private String translatedName;
    private String imageUrl;

    protected Category() {
        super();
    }

    public Category(String name, String translatedName, String imageUrl) {
        this();
        this.id = UUID.randomUUID();
        this.name = name;
        this.translatedName = translatedName;
        this.imageUrl = imageUrl;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTranslatedName() {
        return translatedName;
    }
}
