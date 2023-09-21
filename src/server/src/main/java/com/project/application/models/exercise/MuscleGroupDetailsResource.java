package com.project.application.models.exercise;

import com.project.domain.workout.MuscleGroup;
import io.micronaut.serde.annotation.Serdeable;

import java.util.UUID;

@Serdeable
public record MuscleGroupDetailsResource(UUID id, String name, String translatedName) {
    public static MuscleGroupDetailsResource from(MuscleGroup muscleGroup) {
        return new MuscleGroupDetailsResource(muscleGroup.getId(), muscleGroup.getName(), muscleGroup.getTranslatedName());
    }
}
