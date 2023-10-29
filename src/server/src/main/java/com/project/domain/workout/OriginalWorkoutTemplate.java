package com.project.domain.workout;

import com.project.domain.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "original_workout_templates")
public class OriginalWorkoutTemplate extends BaseEntity {
    @Id
    private UUID id;

    private String name;

    @OneToMany(mappedBy = "originalWorkoutTemplate")
    private final List<WorkoutExercise> exercises;

    protected OriginalWorkoutTemplate() {
        super();
        this.exercises = new ArrayList<>();
    }

    public OriginalWorkoutTemplate(String name, List<WorkoutExercise> exercises) {
        this();
        this.id = UUID.randomUUID();
        this.name = name;
        this.exercises.addAll(exercises);
    }

    public UUID getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public List<WorkoutExercise> getExercises() {
        return this.exercises;
    }
}
