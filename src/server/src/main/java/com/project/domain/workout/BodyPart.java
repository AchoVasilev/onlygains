package com.project.domain.workout;

import com.project.domain.BaseEntity;
import com.project.domain.post.Post;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class BodyPart extends BaseEntity {
    @Id
    private UUID id;

    private String name;

    @ManyToMany(mappedBy = "bodyParts")
    private final Set<Exercise> exercises;

    protected BodyPart() {
        super();
        this.id = UUID.randomUUID();
        this.exercises = new HashSet<>();
    }

    public BodyPart(String name) {
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
