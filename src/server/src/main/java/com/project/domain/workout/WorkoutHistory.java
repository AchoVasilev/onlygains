package com.project.domain.workout;

import com.project.domain.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class WorkoutHistory extends BaseEntity {
    @Id
    private final UUID id;

    private UUID workoutId;

    protected WorkoutHistory() {
        super();
        this.id = UUID.randomUUID();
    }

    public WorkoutHistory(UUID workoutId) {
        super();
        this.id = UUID.randomUUID();
        this.workoutId = workoutId;
    }

    public UUID getId() {
        return this.id;
    }

    public UUID getWorkoutId() {
        return this.workoutId;
    }
}
