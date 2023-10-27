package com.project.domain.workout;

import com.project.domain.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
@Entity(name = "workout_templates")
public class WorkoutTemplate extends BaseEntity {
    @Id
    private UUID id;

    private String name;

    @OneToMany(mappedBy = "workoutTemplate")
    private final List<Workout> workouts;

    @ManyToMany(mappedBy = "workoutTemplates")
    private final Set<Exercise> exercises;

    protected WorkoutTemplate() {
        super();
        this.exercises = new HashSet<>();
        this.workouts = new ArrayList<>();
    }

    public WorkoutTemplate(String name) {
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

    public Set<Exercise> getExercises() {
        return this.exercises;
    }

    public void addExercise(Exercise exercise) {
        this.exercises.add(exercise);
    }

    public List<Workout> getWorkouts() {
        return this.workouts;
    }

    public void addWorkout(Workout workout) {
        this.workouts.add(workout);
    }
}
