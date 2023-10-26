package com.project.application.models.workout;

import java.util.List;
import java.util.UUID;

public record WorkoutExerciseResource(UUID id, List<SetResource> sets) {
}
