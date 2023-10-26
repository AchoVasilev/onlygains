package com.project.application.models.exercise;

import com.project.domain.workout.MuscleGroup;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record MuscleGroupDetailsResource(String id, String name, String translatedName) {
    public static MuscleGroupDetailsResource from(MuscleGroup muscleGroup) {
        return new MuscleGroupDetailsResource(muscleGroup.getId(), muscleGroup.getName(), muscleGroup.getTranslatedName());
    }
}
