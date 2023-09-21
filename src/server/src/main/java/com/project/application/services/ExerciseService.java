package com.project.application.services;

import com.project.application.models.exercise.ExerciseDetailsResource;
import com.project.domain.workout.Exercise;
import com.project.infrastructure.data.ExerciseRepository;
import com.project.infrastructure.exceptions.EntityNotFoundException;
import jakarta.inject.Singleton;

import java.util.UUID;

@Singleton
public class ExerciseService {
    private final ExerciseRepository exerciseRepository;

    public ExerciseService(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    public ExerciseDetailsResource getBy(UUID id) {
        var exercise = this.exerciseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Exercise.class, id));

        return ExerciseDetailsResource.from(exercise);
    }
}
