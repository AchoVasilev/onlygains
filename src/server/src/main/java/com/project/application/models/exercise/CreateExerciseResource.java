package com.project.application.models.exercise;

import io.micronaut.serde.annotation.Serdeable;
import jakarta.annotation.Nonnull;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Serdeable
public record CreateExerciseResource(
        @Nonnull String name,
        @Nonnull String translatedName,
        @Nonnull String description,
        @Nonnull String gifUrl,
        @Nonnull String imageUrl,
        Optional<List<String>> mainMuscleGroupsIds,
        Optional<List<String>> synergisticMuscleGroupsIds,
        Optional<List<UUID>> variations,
        Optional<List<UUID>> equipment) {
}
