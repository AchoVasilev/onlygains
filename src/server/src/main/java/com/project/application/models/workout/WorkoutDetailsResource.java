package com.project.application.models.workout;

import com.project.domain.workout.Workout;

import java.util.List;
import java.util.UUID;

public record WorkoutDetailsResource(UUID id, String workoutTemplateName, List<WorkoutExerciseDetailsResource> exercises) {
    public static WorkoutDetailsResource from(Workout workout) {
        return new WorkoutDetailsResource(workout.getId(), workout.getWorkoutTemplate().getName(),
                workout.getExercises().stream().map(WorkoutExerciseDetailsResource::from)
                        .toList());
    }
}
