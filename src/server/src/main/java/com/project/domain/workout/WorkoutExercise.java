package com.project.domain.workout;

import com.project.domain.BaseEntity;
import com.project.utilities.FindUtil;
import com.project.utilities.Time;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

@Entity(name = "workout_exercises")
public class WorkoutExercise extends BaseEntity {

    @Id
    private final UUID id;

    private UUID exerciseId;

    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "exercise")
    private final List<WorkoutSet> sets;

    @ManyToOne(fetch = FetchType.LAZY)
    private WorkoutTemplate workoutTemplate;

    @ManyToOne(fetch = FetchType.LAZY)
    private OriginalWorkoutTemplate originalWorkoutTemplate;

    protected WorkoutExercise() {
        super();
        this.id = UUID.randomUUID();
        this.sets = new ArrayList<>();
    }

    protected WorkoutExercise(Exercise exercise) {
        this();
        this.fromExercise(exercise);
        this.sets.add(WorkoutSet.from(0, 10));
        this.sets.get(0).setExercise(this);
    }

    protected WorkoutExercise(Exercise exercise, List<WorkoutSet> sets) {
        this();
        this.fromExercise(exercise);
        this.setSets(sets);
    }

    protected WorkoutExercise(Exercise exercise, OriginalWorkoutTemplate workoutTemplate, List<WorkoutSet> sets) {
        this();
        this.fromExercise(exercise);
        this.originalWorkoutTemplate = workoutTemplate;
        this.setSets(sets);
    }

    protected WorkoutExercise(UUID exerciseId, WorkoutTemplate workoutTemplate, List<WorkoutSet> sets) {
        this();
        this.exerciseId = exerciseId;
        this.workoutTemplate = workoutTemplate;
        this.setSets(sets);
    }

    public UUID getId() {
        return this.id;
    }

    public UUID getExerciseId() {
        return this.exerciseId;
    }

    public String getName() {
        return this.name;
    }

    public List<WorkoutSet> getSets() {
        return this.sets;
    }

    public WorkoutTemplate getWorkoutTemplate() {
        return this.workoutTemplate;
    }

    public OriginalWorkoutTemplate getOriginalWorkoutTemplate() {
        return this.originalWorkoutTemplate;
    }

    public void setWorkoutTemplate(WorkoutTemplate workoutTemplate) {
        this.workoutTemplate = workoutTemplate;
        this.setModifiedAt(Time.utcNow());
    }

    public static WorkoutExercise from(Exercise exercise) {
        return new WorkoutExercise(exercise);
    }

    public static WorkoutExercise from(Exercise exercise, List<WorkoutSet> sets) {
        return new WorkoutExercise(exercise, sets);
    }

    public static WorkoutExercise from(Exercise exercise, WorkoutTemplate workoutTemplate, List<WorkoutSet> sets) {
        return new WorkoutExercise(exercise.getId(), workoutTemplate, sets);
    }

    public static WorkoutExercise from(Exercise exercise, OriginalWorkoutTemplate workoutTemplate, List<WorkoutSet> sets) {
        return new WorkoutExercise(exercise, workoutTemplate, sets);
    }

    public void updateSet(UUID setId, double weight, int repetitions) {
        Predicate<WorkoutSet> predicate = ws -> ws.getId().equals(setId);
        var set = FindUtil.findByProperty(this.sets, predicate);
        set.ifPresent(s -> {
            s.setWeight(weight);
            s.setRepetitions(repetitions);
        });
    }

    public void addSet(int repetitions, double weight) {
        var set = WorkoutSet.from(weight, repetitions);
        set.setExercise(this);
        this.sets.add(set);
        this.setModifiedAt(Time.utcNow());
    }

    private void fromExercise(Exercise exercise) {
        this.exerciseId = exercise.getId();
        this.name = exercise.getName();
    }

    private void setSets(List<WorkoutSet> sets) {
        sets.forEach(s -> s.setExercise(this));
        this.sets.addAll(sets);
    }
}
