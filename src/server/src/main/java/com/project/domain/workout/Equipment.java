package com.project.domain.workout;

import com.project.domain.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class Equipment extends BaseEntity {
    @Id
    private final UUID id;

    private String name;
    @ManyToMany(mappedBy = "equipment")
    private final Set<Exercise> exercises;

    protected Equipment() {
        super();
        this.id = UUID.randomUUID();
        this.exercises = new HashSet<>();
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

    public Set<Exercise> getExercises() {
        return this.exercises;
    }
}
