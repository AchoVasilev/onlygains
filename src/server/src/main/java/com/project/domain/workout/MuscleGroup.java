package com.project.domain.workout;

import com.project.domain.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity(name = "muscle_groups")
public class MuscleGroup extends BaseEntity {
    @Id
    private final UUID id;

    private String name;

    private String translatedName;

    @ManyToMany(mappedBy = "muscleGroups")
    private final Set<Exercise> exercises;

    protected MuscleGroup() {
        super();
        this.id = UUID.randomUUID();
        this.exercises = new HashSet<>();
    }

    public MuscleGroup(String name, String translatedName) {
        this();
        this.name = name;
        this.translatedName = translatedName;
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

    public String getTranslatedName() {
        return this.translatedName;
    }
}
