package com.project.domain.post;

import com.project.domain.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Post extends BaseEntity {
    @Id
    private UUID id;

    private String title;
    private String text;

    private Post() {}

    public Post(String title, String text) {
        super();
        this.id = UUID.randomUUID();
        this.title = title;
        this.text = text;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}
