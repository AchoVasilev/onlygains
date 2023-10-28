package com.project.domain.workout;

import com.project.domain.BaseEntity;
import com.project.utilities.Time;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Entity(name = "workout_templates")
public class WorkoutTemplate extends BaseEntity {
    @Id
    private UUID id;

    private String name;

    @OneToMany(mappedBy = "workoutTemplate")
    private final List<Workout> workouts;

    @OneToMany(mappedBy = "workout")
    private final List<WorkoutExercise> exercises;

    protected WorkoutTemplate() {
        super();
        this.exercises = new ArrayList<>();
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

    public List<WorkoutExercise> getExercises() {
        return this.exercises;
    }

    public void addExercise(WorkoutExercise exercise) {
        exercise.setWorkoutTemplate(this);
        this.exercises.add(exercise);
        this.setModifiedAt(Time.utcNow());
    }

    public List<Workout> getWorkouts() {
        return this.workouts;
    }

    public void addWorkout(Workout workout) {
        this.workouts.add(workout);
        this.setModifiedAt(Time.utcNow());
    }
}
