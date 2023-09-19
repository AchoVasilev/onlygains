package com.project.domain.workout;

import com.project.domain.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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

    private String translatedName;
    private String description;
    private String imageUrl;
    private String gifUrl;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "exercise")
    private final List<com.project.domain.workout.Set> sets;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "exercises_workouts", joinColumns = @JoinColumn(name = "exercise_id"),
            inverseJoinColumns = @JoinColumn(name = "workout_id"))
    private final Set<MuscleGroup> muscleGroups;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workout_template_id")
    private WorkoutTemplate workoutTemplate;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "exercises_musclegroups", joinColumns = @JoinColumn(name = "exercise_id"),
            inverseJoinColumns = @JoinColumn(name = "musclegroup_id"))
    private final Set<Workout> workouts;

    @Column(name = "parent_id")
    private UUID parentId;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "parent_id")
    private final List<Exercise> variations;

    protected Exercise() {
        super();
        this.id = UUID.randomUUID();
        this.muscleGroups = new HashSet<>();
        this.sets = new ArrayList<>();
        this.workouts = new HashSet<>();
        this.variations = new ArrayList<>();
    }

    public Exercise(String name, String translatedName, String description, String imageUrl) {
        this();
        this.name = name;
        this.translatedName = translatedName;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public UUID getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getTranslatedName() {
        return this.translatedName;
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
        return this.description;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public WorkoutTemplate getWorkoutTemplate() {
        return this.workoutTemplate;
    }

    public Set<Workout> getWorkouts() {
        return this.workouts;
    }

    public void setWorkoutTemplate(WorkoutTemplate workoutTemplate) {
        this.workoutTemplate = workoutTemplate;
    }

    public UUID getParentId() {
        return this.parentId;
    }

    public void setParentId(UUID parentId) {
        this.parentId = parentId;
    }

    public List<Exercise> getVariations() {
        return this.variations;
    }
}
