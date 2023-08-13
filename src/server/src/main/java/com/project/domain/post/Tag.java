package com.project.domain.post;

import com.project.domain.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Tag extends BaseEntity {
    @Id
    private UUID id;
    private String name;

    protected Tag() {}

    public Tag(String name) {
        super();
        this.id = UUID.randomUUID();
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
