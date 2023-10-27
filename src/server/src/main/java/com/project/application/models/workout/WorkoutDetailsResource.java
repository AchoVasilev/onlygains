package com.project.application.models.workout;

import com.project.domain.workout.Workout;
import io.micronaut.serde.annotation.Serdeable;

import java.util.List;
import java.util.UUID;

@Serdeable
public record WorkoutDetailsResource(UUID id, String workoutTemplateName,
                                     List<WorkoutExerciseDetailsResource> exercises) {
    public static WorkoutDetailsResource from(Workout workout) {
        return new WorkoutDetailsResource(workout.getId(),
                workout.getWorkoutTemplate() != null ? workout.getWorkoutTemplate().getName() : null,
                workout.getExercises().isEmpty() ? List.of() : workout.getExercises().stream().map(WorkoutExerciseDetailsResource::from)
                        .toList());
    }
}
