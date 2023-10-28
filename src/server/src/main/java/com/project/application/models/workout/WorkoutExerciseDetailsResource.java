package com.project.application.models.workout;

import com.project.domain.workout.Exercise;
import com.project.domain.workout.WorkoutExercise;
import io.micronaut.serde.annotation.Serdeable;

import java.util.List;
import java.util.UUID;

@Serdeable
public record WorkoutExerciseDetailsResource(UUID id, List<SetResource> sets) {
    public static WorkoutExerciseDetailsResource from(WorkoutExercise exercise) {
        return new WorkoutExerciseDetailsResource(exercise.getId(), exercise.getSets().stream().map(SetResource::from)
                .toList());
    }
}
