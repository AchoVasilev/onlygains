package com.project.application.services;

import com.project.application.models.workout.CreateWorkoutTemplateResource;
import com.project.application.models.workout.WorkoutTemplateResource;
import com.project.domain.workout.WorkoutExercise;
import com.project.domain.workout.WorkoutSet;
import com.project.domain.workout.WorkoutTemplate;
import com.project.infrastructure.data.WorkoutTemplateRepository;
import com.project.infrastructure.exceptions.EntityNotFoundException;
import io.micronaut.transaction.annotation.Transactional;
import jakarta.inject.Singleton;
import org.slf4j.Logger;

import java.util.UUID;

@Singleton
public class WorkoutTemplateService {
    private final WorkoutTemplateRepository workoutTemplateRepository;

    public WorkoutTemplateService(WorkoutTemplateRepository workoutTemplateRepository) {
        this.workoutTemplateRepository = workoutTemplateRepository;
    }

    @Transactional(readOnly = true)
    public WorkoutTemplate findById(UUID templateId) {
        return this.workoutTemplateRepository.findById(templateId)
                .orElseThrow(() -> new EntityNotFoundException(WorkoutTemplate.class, templateId));
    }
}
