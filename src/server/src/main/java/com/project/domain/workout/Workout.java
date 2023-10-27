package com.project.domain.workout;

import com.project.domain.BaseEntity;
import com.project.utilities.Time;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity(name = "workouts")
public class Workout extends BaseEntity {
    @Id
    private final UUID id;

    @ManyToOne
    @JoinColumn(name = "workout_template_id")
    private WorkoutTemplate workoutTemplate;

    @ManyToMany(mappedBy = "workouts")
    private final Set<Exercise> exercises;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workout_history_id")
    private WorkoutHistory workoutHistory;

    public Workout() {
        super();
        this.id = UUID.randomUUID();
        this.exercises = new HashSet<>();
    }

    public UUID getId() {
        return this.id;
    }

    public WorkoutTemplate getWorkoutTemplate() {
        return this.workoutTemplate;
    }

    public Set<Exercise> getExercises() {
        return this.exercises;
    }

    public void finish(WorkoutTemplate workoutTemplate, List<Exercise> additionalExercises) {
        this.workoutHistory.addWorkout(this);
        workoutTemplate.addWorkout(this);
        this.workoutTemplate = workoutTemplate;
        this.exercises.addAll(additionalExercises);
        this.setModifiedAt(Time.utcNow());
    }
}
