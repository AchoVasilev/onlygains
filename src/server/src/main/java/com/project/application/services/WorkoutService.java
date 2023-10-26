package com.project.application.services;

import com.project.application.models.workout.WorkoutDetailsResource;
import com.project.application.models.workout.WorkoutExerciseResource;
import com.project.domain.workout.Exercise;
import com.project.domain.workout.Workout;
import com.project.infrastructure.data.WorkoutRepository;
import io.micronaut.transaction.annotation.Transactional;
import jakarta.inject.Singleton;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Singleton
public class WorkoutService {
    private final WorkoutRepository workoutRepository;
    private final WorkoutTemplateService workoutTemplateService;
    private final ExerciseService exerciseService;

    public WorkoutService(WorkoutRepository workoutRepository, WorkoutTemplateService workoutTemplateService, ExerciseService exerciseService) {
        this.workoutRepository = workoutRepository;
        this.workoutTemplateService = workoutTemplateService;
        this.exerciseService = exerciseService;
    }

    @Transactional
    public WorkoutDetailsResource perform(UUID workoutTemplateId, List<WorkoutExerciseResource> additionalExercises) {
        var workoutTemplate = this.workoutTemplateService.findById(workoutTemplateId);
        var exercises = new ArrayList<Exercise>();

        additionalExercises.forEach(additionalExercise -> {
            var exercise = this.exerciseService.getBy(additionalExercise.id());
            additionalExercise.sets().forEach(set -> exercise.addSet(set.repetitions(), set.weight()));
            exercises.add(exercise);
        });

        var workout = new Workout();
        workout.perform(workoutTemplate, exercises);
        var result = this.workoutRepository.save(workout);

        return WorkoutDetailsResource.from(result);
    }
}
