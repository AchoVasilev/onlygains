package com.project.application.services;

import com.project.application.models.exercise.CreateExerciseResource;
import com.project.application.models.exercise.ExerciseDetailsResource;
import com.project.application.models.exercise.ExerciseResource;
import com.project.domain.workout.Exercise;
import com.project.infrastructure.data.ExerciseRepository;
import com.project.infrastructure.exceptions.DuplicateEntryException;
import com.project.infrastructure.exceptions.EntityNotFoundException;
import io.micronaut.transaction.annotation.Transactional;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.UUID;

@Singleton
public class ExerciseService {
    private final ExerciseRepository exerciseRepository;
    private final MuscleGroupService muscleGroupService;
    private final EquipmentService equipmentService;

    public ExerciseService(ExerciseRepository exerciseRepository, MuscleGroupService muscleGroupService, EquipmentService equipmentService) {
        this.exerciseRepository = exerciseRepository;
        this.muscleGroupService = muscleGroupService;
        this.equipmentService = equipmentService;
    }

    @Transactional(readOnly = true)
    public Exercise getBy(UUID id) {
        return this.exerciseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Exercise.class, id));
    }

    @Transactional
    public ExerciseDetailsResource create(CreateExerciseResource exerciseResource) {
        var exerciseOpt = this.exerciseRepository.findByName(exerciseResource.name());
        if (exerciseOpt.isPresent()) {
            throw new DuplicateEntryException(Exercise.class, exerciseOpt.get().getId());
        }

        var exercise = new Exercise(exerciseResource.name(), exerciseResource.translatedName(), exerciseResource.description(), exerciseResource.imageUrl(), exerciseResource.gifUrl());
        exerciseResource.variations().ifPresent(variationIds -> {
            var variations = this.getBy(variationIds);
            exercise.addVariations(variations);
        });

        exerciseResource.mainMuscleGroupsIds().ifPresent(muscleGroupIds -> {
            var muscleGroups = this.muscleGroupService.findBy(muscleGroupIds);
            exercise.addMuscleGroups(muscleGroups);
            exercise.addMainMuscleGroupsIds(muscleGroupIds);
        });

        exerciseResource.synergisticMuscleGroupsIds().ifPresent(muscleGroupIds -> {
            var muscleGroups = this.muscleGroupService.findBy(muscleGroupIds);
            exercise.addMuscleGroups(muscleGroups);
            exercise.addSynergisticMuscleGroupIds(muscleGroupIds);
        });

        exerciseResource.equipment().ifPresent(equipmentIds -> {
            var equipment = this.equipmentService.getBy(equipmentIds);
            exercise.addEquipment(equipment);
        });

        var result = this.exerciseRepository.save(exercise);

        return ExerciseDetailsResource.from(result);
    }

    @Transactional(readOnly = true)
    public List<ExerciseResource> getBy(String search) {
        return this.exerciseRepository.findSimilarExercises(search);
    }

    @Transactional(readOnly = true)
    public List<Exercise> getBy(List<UUID> ids) {
        return this.exerciseRepository.findByIdIn(ids);
    }
}
