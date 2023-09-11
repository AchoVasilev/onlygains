package com.project.domain.workout;

import com.project.domain.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Workout extends BaseEntity {
    @Id
    private UUID id;

    private String name;

    @OneToMany
    private final List<Exercise> exercises;

    protected Workout() {
        super();
        this.exercises = new ArrayList<>();
    }

    public Workout(String name) {
        this();
        this.id = UUID.randomUUID();
        this.name = name;
    }

    public UUID getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public List<Exercise> getExercises() {
        return this.exercises;
    }

    public void addExercise(Exercise exercise) {
        this.exercises.add(exercise);
    }
}
