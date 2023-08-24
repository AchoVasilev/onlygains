package com.project.domain.post;

import com.project.domain.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity(name = "tags")
public class Tag extends BaseEntity {
    @Id
    private UUID id;
    private String name;
    private String translatedName;

    protected Tag() {}

    public Tag(String name, String translatedName) {
        super();
        this.id = UUID.randomUUID();
        this.name = name;
        this.translatedName = translatedName;
    }

    public UUID getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getTranslatedName() {
        return this.translatedName;
    }
}
