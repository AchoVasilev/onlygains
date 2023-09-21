package com.project.application.models.exercise;

import com.project.domain.workout.Exercise;

import java.util.List;
import java.util.UUID;

public record ExerciseDetailsResource(UUID id, UUID parentId, String name, String gifUrl, String translatedName,
                                      String description, List<MuscleGroupDetailsResource> muscleGroups,
                                      List<ExerciseResource> variations, List<EquipmentResource> equipment) {

    public static ExerciseDetailsResource from(Exercise exercise) {
        return new ExerciseDetailsResource(exercise.getId(), exercise.getParentId(), exercise.getName(),
                exercise.getGifUrl(), exercise.getTranslatedName(), exercise.getDescription(),
                exercise.getMuscleGroups().stream().map(MuscleGroupDetailsResource::from).toList(),
                exercise.getVariations().stream().map(ExerciseResource::from).toList(),
                exercise.getEquipment().stream().map(EquipmentResource::from).toList());
    }
}
