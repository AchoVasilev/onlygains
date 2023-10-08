package com.project.application.models.exercise;

import com.project.domain.workout.Exercise;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.annotation.Nullable;

import java.util.UUID;

@Serdeable
public record ExerciseResource(UUID id, @Nullable String imageUrl, @Nullable String name, @Nullable String translatedName) {
    public static ExerciseResource from(Exercise exercise) {
        return new ExerciseResource(exercise.getId(), exercise.getImageUrl(), exercise.getName(), exercise.getTranslatedName());
    }
}
