package com.project.application.models.workout;

import com.project.domain.workout.Workout;

import java.util.UUID;

public record WorkoutDetailsResource(UUID id, ) {
    public static WorkoutDetailsResource from(Workout workout) {
        return new WorkoutDetailsResource();
    }
}
