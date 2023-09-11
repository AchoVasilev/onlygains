package com.project.domain.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public class Repetitions {
    private int reps;
    private String weightType;

    protected Repetitions() {}

    private Repetitions(int reps) {
        this.reps = reps;
        this.weightType = "KG";
    }

    public static Repetitions from(int weight) {
        return new Repetitions(weight);
    }

    public int getReps() {
        return this.reps;
    }

    public String getWeightType() {
        return this.weightType;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }
}
