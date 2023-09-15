package com.project.domain.workout;

import com.project.domain.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity(name = "workouts")
public class Workout extends BaseEntity {
    @Id
    private final UUID id;

    @OneToMany(mappedBy = "workout")
    private final List<WorkoutTemplate> workoutTemplates;

    @ManyToMany(mappedBy = "workout")
    private final Set<Exercise> exercises;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workout_history_id")
    private WorkoutHistory workoutHistory;

    public Workout() {
        super();
        this.id = UUID.randomUUID();
        this.workoutTemplates = new ArrayList<>();
        this.exercises = new HashSet<>();
    }

    public UUID getId() {
        return this.id;
    }

    public List<WorkoutTemplate> getWorkoutTemplates() {
        return this.workoutTemplates;
    }

    public Set<Exercise> getExercises() {
        return this.exercises;
    }
}