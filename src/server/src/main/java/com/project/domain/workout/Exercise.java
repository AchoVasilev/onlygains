package com.project.domain.workout;

import com.project.domain.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity(name = "exercises")
public class Exercise extends BaseEntity {
    @Id
    private final UUID id;

    private String name;
    private String description;
    private String videoUrl;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "exercise")
    private final List<com.project.domain.workout.Set> sets;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "exercises_musclegroups", joinColumns = @JoinColumn(name = "exercise_id"),
            inverseJoinColumns = @JoinColumn(name = "musclegroup_id"))
    private final Set<MuscleGroup> muscleGroups;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workout_template_id")
    private WorkoutTemplate workoutTemplate;

    protected Exercise() {
        super();
        this.id = UUID.randomUUID();
        this.muscleGroups = new HashSet<>();
        this.sets = new ArrayList<>();
    }

    public Exercise(String name, String description, String videoUrl) {
        this();
        this.name = name;
        this.description = description;
        this.videoUrl = videoUrl;
    }

    public UUID getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public List<com.project.domain.workout.Set> getSets() {
        return this.sets;
    }

    public Set<MuscleGroup> getMuscleGroups() {
        return this.muscleGroups;
    }

    public void addSet(com.project.domain.workout.Set set) {
        this.sets.add(set);
    }

    public String getDescription() {
        return description;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public WorkoutTemplate getWorkoutTemplate() {
        return workoutTemplate;
    }
}
