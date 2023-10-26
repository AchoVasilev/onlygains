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
public class Set extends BaseEntity {
    @Id
    private UUID id;

    @Embedded
    private Weight weight;
    private int repetitions;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;

    private Set(double weight, int repetitions, Exercise exercise) {
        super();
        this.id = UUID.randomUUID();
        this.weight = Weight.from(weight);
        this.repetitions = repetitions;
        this.exercise = exercise;
    }

    protected Set() {
    }

    public static Set from(double weight, int repetitions, Exercise exercise) {
        return new Set(weight, repetitions, exercise);
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

    public Exercise getExercise() {
        return exercise;
    }
}
