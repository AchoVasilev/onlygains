package com.project.application.services;

import com.project.application.models.workout.CreateWorkoutExerciseResource;
import com.project.application.models.workout.UpdateWorkoutExerciseResource;
import com.project.application.models.workout.WorkoutDetailsResource;
import com.project.application.models.workout.WorkoutExerciseResource;
import com.project.domain.workout.Workout;
import com.project.domain.workout.WorkoutExercise;
import com.project.domain.workout.WorkoutSet;
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
    private final WorkoutHistoryService workoutHistoryService;

    public WorkoutService(
            WorkoutRepository workoutRepository,
            WorkoutTemplateService workoutTemplateService,
            ExerciseService exerciseService,
            WorkoutHistoryService workoutHistoryService) {
        this.workoutRepository = workoutRepository;
        this.workoutTemplateService = workoutTemplateService;
        this.exerciseService = exerciseService;
        this.workoutHistoryService = workoutHistoryService;
    }

    @Transactional
    public WorkoutDetailsResource start(UUID workoutTemplateId) {
        var workoutTemplate = this.workoutTemplateService.findById(workoutTemplateId);
        var startedWorkout = Workout.start(workoutTemplate);
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
    public void updateExerciseInWorkout(UpdateWorkoutExerciseResource workoutExerciseResource) {
        var workout = this.findById(workoutExerciseResource.workoutId());
        var exercise = this.exerciseService.getBy(workoutExerciseResource.exerciseId());

        workoutExerciseResource.sets()
                .forEach(set -> workout.updateExercise(exercise.getId(), set.id(), set.weight(), set.repetitions()));

        this.workoutRepository.save(workout);

        log.info("Updated exercise in workout. [exerciseId={}, workoutId={}]", exercise.getId(), workout.getId());
    }

    @Transactional
    public void addExerciseToWorkout(UUID workoutId, CreateWorkoutExerciseResource newExercise) {
        var exercise = this.exerciseService.getBy(newExercise.exerciseId());
        var workout = this.findById(workoutId);
        if (newExercise.sets().isEmpty()) {
            workout.addExercise(WorkoutExercise.from(exercise, workout));
        } else {
            var sets = newExercise.sets().stream().map(s -> WorkoutSet.from(s.weight(), s.repetitions())).toList();
            workout.addExercise(WorkoutExercise.from(exercise, workout, sets));
        }

        this.workoutRepository.save(workout);

        log.info("Added exercise to workout. [exerciseId={}, workoutId={}]", exercise.getId(), workout.getId());
    }

    @Transactional
    public void finish(UUID workoutId) {
        var workout = this.findById(workoutId);
        workout.finish();

        this.workoutRepository.save(workout);
        this.workoutHistoryService.createHistoryFor(workout);

        log.info("Workout finished. [workoutId={}]", workout.getId());
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

    private List<WorkoutExercise> processAdditionalExercises(List<WorkoutExerciseResource> additionalExercises, Workout workout) {
        var exercises = new ArrayList<WorkoutExercise>();
        additionalExercises.forEach(additionalExercise -> {
            var exercise = this.exerciseService.getBy(additionalExercise.id());
            additionalExercise.sets().forEach(set -> {
                var workoutExercise = WorkoutExercise.from(exercise, workout);
                workoutExercise.addSet(set.repetitions(), set.weight());
                exercises.add(workoutExercise);
            });
        });

        return exercises;
    }
}
