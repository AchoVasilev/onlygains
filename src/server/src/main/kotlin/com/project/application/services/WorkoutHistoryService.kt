package com.project.application.services

import com.project.domain.workout.Workout
import com.project.domain.workout.WorkoutHistory
import com.project.infrastructure.data.WorkoutHistoryRepository
import jakarta.inject.Singleton

@Singleton
open class WorkoutHistoryService(private val workoutHistoryRepository: WorkoutHistoryRepository) {
    open fun createHistoryFor(workout: Workout) {
        workoutHistoryRepository.save(WorkoutHistory(workout.id))
    }
}
