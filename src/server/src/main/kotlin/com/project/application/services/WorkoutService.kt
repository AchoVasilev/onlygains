package com.project.application.services

import com.project.application.models.workout.CreateWorkoutExerciseResource
import com.project.application.models.workout.CreateWorkoutSetResource
import com.project.application.models.workout.UpdateWorkoutExerciseResource
import com.project.application.models.workout.WorkoutDetailsResource
import com.project.application.models.workout.WorkoutSetResource
import com.project.application.services.LoggerProvider.getLogger
import com.project.domain.workout.Workout
import com.project.domain.workout.WorkoutExercise
import com.project.domain.workout.WorkoutSet
import com.project.domain.workout.WorkoutTemplate
import com.project.infrastructure.data.WorkoutRepository
import com.project.infrastructure.exceptions.EntityNotFoundException
import io.micronaut.transaction.annotation.Transactional
import jakarta.inject.Singleton
import java.util.UUID

@Singleton
open class WorkoutService(
    private val workoutRepository: WorkoutRepository,
    private val originalWorkoutTemplateService: OriginalWorkoutTemplateService,
    private val exerciseService: ExerciseService,
    private val workoutHistoryService: WorkoutHistoryService
) {
    @Transactional
    open fun start(originalWorkoutTemplateId: UUID): WorkoutDetailsResource {
        val workoutTemplate = originalWorkoutTemplateService.findBy(originalWorkoutTemplateId)
        val startedWorkout = Workout.start(WorkoutTemplate.from(workoutTemplate))
        val workout = workoutRepository.save(startedWorkout)

        log.info("Workout started. [workoutId={}]", workout.id)

        return WorkoutDetailsResource.from(workout)
    }

    @Transactional
    open fun startEmptyWorkout(): WorkoutDetailsResource {
        val workout = workoutRepository.save(Workout.startEmptyWorkout())

        log.info("Workout started. [workoutId={}]", workout.id)

        return WorkoutDetailsResource.from(workout)
    }

    @Transactional
    open fun updateExerciseInWorkout(workoutExerciseResource: UpdateWorkoutExerciseResource): WorkoutDetailsResource {
        val workout = this.findById(workoutExerciseResource.workoutId)
        val exercise = exerciseService.getById(workoutExerciseResource.exerciseId)

        workoutExerciseResource.sets
                .forEach { set: WorkoutSetResource ->
                    if (set.id != null) {
                        workout.updateExercise(exercise.id, set.id, set.weight, set.repetitions)
                    } else {
                        workout.addSetToExercise(exercise.id, set.weight, set.repetitions)
                    }
                }

        val result = workoutRepository.save(workout)

        log.info("Updated exercise in workout. [exerciseId={}, workoutId={}]", exercise.id, workout.id)

        return WorkoutDetailsResource.from(result)
    }

    @Transactional
    open fun addExerciseToWorkout(workoutId: UUID, newExercise: CreateWorkoutExerciseResource): WorkoutDetailsResource {
        val exercise = exerciseService.getById(newExercise.exerciseId)
        val workout = this.findById(workoutId)
        if (newExercise.sets.isEmpty()) {
            workout.addExercise(WorkoutExercise.from(exercise))
        } else {
            val sets = newExercise.sets.map { s: CreateWorkoutSetResource -> WorkoutSet(s.weight, s.repetitions) }
            workout.addExercise(WorkoutExercise.from(exercise, sets))
        }

        val result = workoutRepository.save(workout)
        log.info("Added exercise to workout. [exerciseId={}, workoutId={}]", exercise.id, workout.id)

        return WorkoutDetailsResource.from(result)
    }

    @Transactional
    open fun finish(workoutId: UUID): WorkoutDetailsResource {
        var workout = this.findById(workoutId)
        workout.finish()

        workout = workoutRepository.save(workout)
        workoutHistoryService.createHistoryFor(workout)

        log.info("Workout finished. [workoutId={}]", workout.id)

        return WorkoutDetailsResource.from(workout)
    }

    @Transactional
    open fun cancel(workoutId: UUID) {
        val workout = this.findById(workoutId)
        workout.cancel()
        workoutRepository.save(workout)
        workoutHistoryService.createHistoryFor(workout)

        log.info("Workout cancelled. [workoutId={}]", workout.id)
    }

    private fun findById(id: UUID): Workout {
        return workoutRepository.findById(id)
                .orElseThrow { EntityNotFoundException(Workout::class, id) }
    }

    private companion object {
        @JvmStatic
        private val log = getLogger(WorkoutService::class.java)
    }
}
