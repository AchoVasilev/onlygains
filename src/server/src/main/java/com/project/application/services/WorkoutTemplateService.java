package com.project.application.services;

import com.project.domain.workout.WorkoutTemplate;
import com.project.infrastructure.data.WorkoutTemplateRepository;
import com.project.infrastructure.exceptions.EntityNotFoundException;
import jakarta.inject.Singleton;

import java.util.UUID;

@Singleton
public class WorkoutTemplateService {
    private final WorkoutTemplateRepository workoutTemplateRepository;

    public WorkoutTemplateService(WorkoutTemplateRepository workoutTemplateRepository) {
        this.workoutTemplateRepository = workoutTemplateRepository;
    }

    public WorkoutTemplate findById(UUID templateId) {
        return this.workoutTemplateRepository.findById(templateId)
                .orElseThrow(() -> new EntityNotFoundException(WorkoutTemplate.class, templateId));
    }
}
