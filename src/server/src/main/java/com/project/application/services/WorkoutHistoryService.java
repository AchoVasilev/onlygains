package com.project.application.services;

import com.project.domain.workout.Workout;
import com.project.domain.workout.WorkoutHistory;
import com.project.infrastructure.data.WorkoutHistoryRepository;
import jakarta.inject.Singleton;

@Singleton
public class WorkoutHistoryService {
    private final WorkoutHistoryRepository workoutHistoryRepository;

    public WorkoutHistoryService(WorkoutHistoryRepository workoutHistoryRepository) {
        this.workoutHistoryRepository = workoutHistoryRepository;
    }

    public void createHistoryFor(Workout workout) {
        this.workoutHistoryRepository.save(new WorkoutHistory(workout.getId()));
    }
}
