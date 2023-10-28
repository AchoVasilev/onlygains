package com.project.domain.workout;

import com.project.domain.BaseEntity;
import com.project.domain.valueobjects.Weight;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.UUID;

@Entity(name = "sets")
public class WorkoutSet extends BaseEntity {
    @Id
    private UUID id;

    @Embedded
    private Weight weight;
    private int repetitions;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_id")
    private WorkoutExercise exercise;

    private WorkoutSet(double weight, int repetitions) {
        super();
        this.id = UUID.randomUUID();
        this.weight = Weight.from(weight);
        this.repetitions = repetitions;
    }

    protected WorkoutSet() {
    }

    public static WorkoutSet from(double weight, int repetitions) {
        return new WorkoutSet(weight, repetitions);
    }

    public UUID getId() {
        return id;
    }

    public Weight getWeight() {
        return weight;
    }

    public int getRepetitions() {
        return repetitions;
    }

    public WorkoutExercise getExercise() {
        return exercise;
    }

    public void setExercise(WorkoutExercise workoutExercise) {
        this.exercise = workoutExercise;
    }

    public void setWeight(double weight) {
        this.weight = Weight.from(weight);
    }

    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }
}
