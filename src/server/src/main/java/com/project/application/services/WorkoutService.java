package com.project.application.services;

import com.project.application.models.workout.CreateWorkoutExerciseResource;
import com.project.application.models.workout.UpdateWorkoutExerciseResource;
import com.project.application.models.workout.WorkoutDetailsResource;
import com.project.domain.workout.Workout;
import com.project.domain.workout.WorkoutExercise;
import com.project.domain.workout.WorkoutSet;
import com.project.domain.workout.WorkoutTemplate;
import com.project.infrastructure.data.WorkoutRepository;
import com.project.infrastructure.exceptions.EntityNotFoundException;
import io.micronaut.transaction.annotation.Transactional;
import jakarta.inject.Singleton;
import org.slf4j.Logger;

import java.util.UUID;

@Singleton
public class WorkoutService {
    private static final Logger log = LoggerProvider.getLogger(WorkoutService.class);
    private final WorkoutRepository workoutRepository;
    private final OriginalWorkoutTemplateService originalWorkoutTemplateService;
    private final WorkoutTemplateService workoutTemplateService;
    private final ExerciseService exerciseService;
    private final WorkoutHistoryService workoutHistoryService;

    public WorkoutService(
            WorkoutRepository workoutRepository,
            OriginalWorkoutTemplateService originalWorkoutTemplateService,
            WorkoutTemplateService workoutTemplateService,
            ExerciseService exerciseService,
            WorkoutHistoryService workoutHistoryService) {
        this.workoutRepository = workoutRepository;
        this.originalWorkoutTemplateService = originalWorkoutTemplateService;
        this.workoutTemplateService = workoutTemplateService;
        this.exerciseService = exerciseService;
        this.workoutHistoryService = workoutHistoryService;
    }

    @Transactional
    public WorkoutDetailsResource start(UUID originalWorkoutTemplateId) {
        var workoutTemplate = this.originalWorkoutTemplateService.findBy(originalWorkoutTemplateId);
        var startedWorkout = Workout.start(WorkoutTemplate.from(workoutTemplate));
        var workout = this.workoutRepository.save(startedWorkout);

        log.info("Workout started. [workoutId={}]", workout.getId());

        return WorkoutDetailsResource.from(workout);
    }

    @Transactional
    public WorkoutDetailsResource startEmptyWorkout() {
        var workout = this.workoutRepository.save(Workout.startEmptyWorkout());

        log.info("Workout started. [workoutId={}]", workout.getId());

        return WorkoutDetailsResource.from(workout);
    }

    @Transactional
    public WorkoutDetailsResource updateExerciseInWorkout(UpdateWorkoutExerciseResource workoutExerciseResource) {
        var workout = this.findById(workoutExerciseResource.workoutId());
        var exercise = this.exerciseService.getBy(workoutExerciseResource.exerciseId());

        workoutExerciseResource.sets()
                .forEach(set -> {
                    if (set.id() != null) {
                        workout.updateExercise(exercise.getId(), set.id(), set.weight(), set.repetitions());
                    } else {
                        workout.addSetToExercise(exercise.getId(), set.weight(), set.repetitions());
                    }
                });

        var result = this.workoutRepository.save(workout);

        log.info("Updated exercise in workout. [exerciseId={}, workoutId={}]", exercise.getId(), workout.getId());

        return WorkoutDetailsResource.from(result);
    }

    @Transactional
    public WorkoutDetailsResource addExerciseToWorkout(UUID workoutId, CreateWorkoutExerciseResource newExercise) {
        var exercise = this.exerciseService.getBy(newExercise.exerciseId());
        var workout = this.findById(workoutId);
        if (newExercise.sets().isEmpty()) {
            workout.addExercise(WorkoutExercise.from(exercise));
        } else {
            var sets = newExercise.sets().stream().map(s -> WorkoutSet.from(s.weight(), s.repetitions())).toList();
            workout.addExercise(WorkoutExercise.from(exercise, sets));
        }

        var result = this.workoutRepository.save(workout);
        log.info("Added exercise to workout. [exerciseId={}, workoutId={}]", exercise.getId(), workout.getId());

        return WorkoutDetailsResource.from(result);
    }

    @Transactional
    public WorkoutDetailsResource finish(UUID workoutId) {
        var workout = this.findById(workoutId);
        workout.finish();

        workout = this.workoutRepository.save(workout);
        this.workoutHistoryService.createHistoryFor(workout);

        log.info("Workout finished. [workoutId={}]", workout.getId());

        return WorkoutDetailsResource.from(workout);
    }

    @Transactional
    public void cancel(UUID workoutId) {
        var workout = this.findById(workoutId);
        workout.cancel();
        this.workoutRepository.save(workout);
        this.workoutHistoryService.createHistoryFor(workout);

        log.info("Workout cancelled. [workoutId={}]", workout.getId());
    }

    private Workout findById(UUID id) {
        return this.workoutRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Workout.class, id));
    }
}
