package com.project.domain.workout;

import com.project.domain.BaseEntity;
import com.project.utilities.Time;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class WorkoutHistory extends BaseEntity {
    @Id
    private final UUID id;

    @OneToMany
    private final List<Workout> workouts;

    public WorkoutHistory() {
        super();
        this.id = UUID.randomUUID();
        this.workouts = new ArrayList<>();
    }

    public UUID getId() {
        return this.id;
    }

    public List<Workout> getWorkouts() {
        return this.workouts;
    }

    public void addWorkout(Workout workout) {
        this.workouts.add(workout);
        this.setModifiedAt(Time.utcNow());
    }
}
