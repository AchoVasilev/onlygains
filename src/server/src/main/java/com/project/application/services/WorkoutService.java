package com.project.application.services;

import com.project.application.models.workout.WorkoutDetailsResource;
import com.project.application.models.workout.WorkoutExerciseResource;
import com.project.domain.workout.Exercise;
import com.project.domain.workout.Workout;
import com.project.infrastructure.data.WorkoutRepository;
import com.project.infrastructure.exceptions.EntityNotFoundException;
import io.micronaut.transaction.annotation.Transactional;
import jakarta.inject.Singleton;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Singleton
public class WorkoutService {
    private static final Logger log = LoggerProvider.getLogger(WorkoutService.class);
    private final WorkoutRepository workoutRepository;
    private final WorkoutTemplateService workoutTemplateService;
    private final ExerciseService exerciseService;

    public WorkoutService(WorkoutRepository workoutRepository, WorkoutTemplateService workoutTemplateService, ExerciseService exerciseService) {
        this.workoutRepository = workoutRepository;
        this.workoutTemplateService = workoutTemplateService;
        this.exerciseService = exerciseService;
    }

    @Transactional
    public void start(UUID workoutTemplateId, List<WorkoutExerciseResource> additionalExercises) {
        var workoutTemplate = this.workoutTemplateService.findById(workoutTemplateId);
        var exercises = this.addSetsToExercises(additionalExercises);
        var startedWorkout = Workout.startEmptyWorkout(workoutTemplate, exercises);
        var workout = this.workoutRepository.save(startedWorkout);

        log.info("Workout started. [workoutId={}]", workout.getId());
    }

    @Transactional
    public WorkoutDetailsResource startEmptyWorkout() {
        var workout = this.workoutRepository.save(Workout.startEmptyWorkout());

        log.info("Workout started. [workoutId={}]", workout.getId());

        return WorkoutDetailsResource.from(workout);
    }

    @Transactional
    public void addExerciseToWorkout(UUID workoutId, WorkoutExerciseResource newExercise) {
        var exercise = this.exerciseService.getBy(newExercise.id());
        var workout = this.findById(workoutId);
        workout.addExercise(exercise);

        log.info("Added exercise to workout. [exerciseId={}, workoutId={}]", exercise.getId(), workout.getId());
    }

    @Transactional
    public void perform(UUID workoutTemplateId, List<WorkoutExerciseResource> additionalExercises, UUID workoutId) {
        var workoutTemplate = this.workoutTemplateService.findById(workoutTemplateId);
        var exercises = this.addSetsToExercises(additionalExercises);

        var workout = this.findById(workoutId);
        workout.finish(workoutTemplate, exercises);

        log.info("Workout finished. [workoutId={}]", workout.getId());
    }

    private Workout findById(UUID id) {
        return this.workoutRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Workout.class, id));
    }

    private List<Exercise> addSetsToExercises(List<WorkoutExerciseResource> additionalExercises) {
        var exercises = new ArrayList<Exercise>();

        additionalExercises.forEach(additionalExercise -> {
            var exercise = this.exerciseService.getBy(additionalExercise.id());
            additionalExercise.sets().forEach(set -> exercise.addSet(set.repetitions(), set.weight()));
            exercises.add(exercise);
        });

        return exercises;
    }
}
