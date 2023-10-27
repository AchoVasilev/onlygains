package com.project.application.models.workout;

import com.project.domain.workout.Exercise;

import java.util.List;
import java.util.UUID;

public record WorkoutExerciseDetailsResource(UUID id, List<SetResource> sets) {
    public static WorkoutExerciseDetailsResource from(Exercise exercise) {
        return new WorkoutExerciseDetailsResource(exercise.getId(), exercise.getSets().stream().map(SetResource::from)
                .toList());
    }
}
