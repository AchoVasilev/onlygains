package com.project.domain.workout;

import com.project.domain.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Equipment extends BaseEntity {
    @Id
    private UUID id;

    private String name;

    protected Equipment() {
        super();
        this.id = UUID.randomUUID();
    }

    public Equipment(String name) {
        this();
        this.name = name;
    }

    public UUID getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }
}
