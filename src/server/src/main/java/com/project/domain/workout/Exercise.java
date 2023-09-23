package com.project.domain.workout;

import com.project.domain.BaseEntity;
import com.project.infrastructure.converters.StringToListConverter;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
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

    @ManyToMany(mappedBy = "variations")
    private final Set<Exercise> exercises;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "exercises_variations", joinColumns = @JoinColumn(name = "exercise_id"),
            inverseJoinColumns = @JoinColumn(name = "variation_id"))
    private final Set<Exercise> variations;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "exercises_equipment", joinColumns = @JoinColumn(name = "exercise_id"),
            inverseJoinColumns = @JoinColumn(name = "equipment_id"))
    private final Set<Equipment> equipment;

    @Column(name = "main_muscle_groups_ids")
    @Convert(converter = StringToListConverter.class)
    private final List<String> mainMuscleGroupsIds;

    @Column(name = "synergistic_muscle_groups_ids")
    @Convert(converter = StringToListConverter.class)
    private final List<String> synergisticMuscleGroupsIds;

    protected Exercise() {
        super();
        this.id = UUID.randomUUID();
        this.muscleGroups = new HashSet<>();
        this.sets = new ArrayList<>();
        this.workouts = new HashSet<>();
        this.variations = new HashSet<>();
        this.exercises = new HashSet<>();
        this.equipment = new HashSet<>();
        this.mainMuscleGroupsIds = new ArrayList<>();
        this.synergisticMuscleGroupsIds = new ArrayList<>();
    }

    public Exercise(String name, String translatedName, String description, String imageUrl, String gifUrl) {
        this();
        this.name = name;
        this.translatedName = translatedName;
        this.description = description;
        this.imageUrl = imageUrl;
        this.gifUrl = gifUrl;
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

    public Set<Exercise> getVariations() {
        return this.variations;
    }

    public Set<Exercise> getExercises() {
        return this.exercises;
    }

    public String getGifUrl() {
        return this.gifUrl;
    }

    public Set<Equipment> getEquipment() {
        return this.equipment;
    }

    public void addVariations(List<Exercise> variations) {
        this.variations.addAll(variations);
    }

    public void addMuscleGroups(List<MuscleGroup> muscleGroups) {
        this.muscleGroups.addAll(muscleGroups);
    }

    public void addEquipment(List<Equipment> equipment) {
        this.equipment.addAll(equipment);
    }

    public List<String> getMainMuscleGroupsIds() {
        return this.mainMuscleGroupsIds;
    }

    public List<String> getSynergisticMuscleGroupsIds() {
        return this.synergisticMuscleGroupsIds;
    }
}
