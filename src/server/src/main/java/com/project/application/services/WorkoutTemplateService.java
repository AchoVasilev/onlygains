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
    private static final Logger log = LoggerProvider.getLogger(WorkoutTemplateService.class);
    private final WorkoutTemplateRepository workoutTemplateRepository;
    private final ExerciseService exerciseService;

    public WorkoutTemplateService(WorkoutTemplateRepository workoutTemplateRepository, ExerciseService exerciseService) {
        this.workoutTemplateRepository = workoutTemplateRepository;
        this.exerciseService = exerciseService;
    }

    @Transactional(readOnly = true)
    public WorkoutTemplate findById(UUID templateId) {
        return this.workoutTemplateRepository.findById(templateId)
                .orElseThrow(() -> new EntityNotFoundException(WorkoutTemplate.class, templateId));
    }

    public WorkoutTemplateResource create(CreateWorkoutTemplateResource workoutTemplateResource) {
        var template = new WorkoutTemplate(workoutTemplateResource.name());

        workoutTemplateResource.exercises().forEach(workoutExerciseResource -> {
            var exercise = this.exerciseService.getBy(workoutExerciseResource.id());
            var sets = workoutExerciseResource.sets().stream().map(s -> WorkoutSet.from(s.weight(), s.repetitions())).toList();
            var workoutExercise = WorkoutExercise.from(exercise, template, sets);

            template.addExercise(workoutExercise);
        });

        this.workoutTemplateRepository.save(template);
        log.info("Successfully created workout template. [workoutTemplateId={}]", template.getId());

        return WorkoutTemplateResource.from(template);
    }
}
