package com.project.application.models.workout;

import java.util.List;
import java.util.UUID;

public record UpdateWorkoutExerciseResource(UUID workoutId, UUID exerciseId, List<SetResource> sets) {
}
