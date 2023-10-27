package com.project.application.models.workout;

import io.micronaut.serde.annotation.Serdeable;

import java.util.List;
import java.util.UUID;

@Serdeable
public record WorkoutExerciseResource(UUID id, List<SetResource> sets) {
}
