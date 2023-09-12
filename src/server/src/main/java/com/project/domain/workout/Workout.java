package com.project.domain.workout;

import com.project.domain.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "workouts")
public class Workout extends BaseEntity {
    @Id
    private final UUID id;

    @OneToMany(mappedBy = "workout")
    private List<WorkoutTemplate> workoutTemplates;

    public Workout() {
        super();
        this.id = UUID.randomUUID();
        this.workoutTemplates = new ArrayList<>();
    }

    public UUID getId() {
        return this.id;
    }

    public List<WorkoutTemplate> getWorkoutTemplates() {
        return this.workoutTemplates;
    }
}
