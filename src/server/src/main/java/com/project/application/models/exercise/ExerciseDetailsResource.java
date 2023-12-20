package com.project.application.models.exercise;

import com.project.domain.workout.Exercise;
import io.micronaut.serde.annotation.Serdeable;

import java.util.List;
import java.util.UUID;

@Serdeable
public record ExerciseDetailsResource(UUID id, String name, String gifUrl, String translatedName,
                                      String description, List<MuscleGroupDetailsResource> muscleGroups,
                                      List<ExerciseResource> variations, List<EquipmentResource> equipment,
                                      List<String> mainMuscleGroupsIds, List<String> synergisticMuscleGroupsIds) {

    public static ExerciseDetailsResource from(Exercise exercise) {
        return new ExerciseDetailsResource(exercise.id, exercise.getName(),
                exercise.getGifUrl(), exercise.getTranslatedName(), exercise.getDescription(),
                exercise.getMuscleGroups().stream().map(MuscleGroupDetailsResource::from).toList(),
                exercise.getVariations().stream().map(ExerciseResource::from).toList(),
                exercise.getEquipment().stream().map(EquipmentResource::from).toList(),
                exercise.getMainMuscleGroupsIds(), exercise.getSynergisticMuscleGroupsIds());
    }
}
