package com.project.application.services;

import com.project.domain.workout.OriginalWorkoutTemplate;
import com.project.infrastructure.data.OriginalWorkoutTemplateRepository;
import com.project.infrastructure.exceptions.EntityNotFoundException;
import jakarta.inject.Singleton;

import java.util.UUID;

@Singleton
public class OriginalWorkoutTemplateService {
    private final OriginalWorkoutTemplateRepository originalWorkoutTemplateRepository;

    public OriginalWorkoutTemplateService(OriginalWorkoutTemplateRepository originalWorkoutTemplateRepository) {
        this.originalWorkoutTemplateRepository = originalWorkoutTemplateRepository;
    }

    public OriginalWorkoutTemplate findBy(UUID id) {
        return this.originalWorkoutTemplateRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(OriginalWorkoutTemplate.class, id));
    }
}
