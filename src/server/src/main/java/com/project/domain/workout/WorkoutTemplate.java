package com.project.domain.workout;

import com.project.domain.BaseEntity;
import com.project.utilities.DateTimeFormatterUtil;
import com.project.utilities.Time;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.project.common.Constants.DATE_FORMAT;

@Entity(name = "workout_templates")
public class WorkoutTemplate extends BaseEntity {
    @Id
    private final UUID id;

    private String name;

    private UUID originalWorkoutTemplateId;

    @OneToMany(mappedBy = "workoutTemplate")
    private final List<WorkoutExercise> exercises;

    protected WorkoutTemplate() {
        super();
        this.id = UUID.randomUUID();
        this.name = this.getCreatedAt().format(DateTimeFormatterUtil.formatterFrom(DATE_FORMAT));
        this.exercises = new ArrayList<>();
    }

    protected WorkoutTemplate(OriginalWorkoutTemplate template) {
        this();
        this.name = template.getName();
        this.originalWorkoutTemplateId = template.getId();
        this.exercises.addAll(template.getExercises());
    }

    public UUID getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public List<WorkoutExercise> getExercises() {
        return this.exercises;
    }

    public UUID getOriginalWorkoutTemplateId() {
        return this.originalWorkoutTemplateId;
    }

    public static WorkoutTemplate from(OriginalWorkoutTemplate originalWorkoutTemplate) {
        return new WorkoutTemplate(originalWorkoutTemplate);
    }

    public static WorkoutTemplate basicTemplate() {
        return new WorkoutTemplate();
    }

    public void addExercise(WorkoutExercise exercise) {
        exercise.setWorkoutTemplate(this);
        this.exercises.add(exercise);
        this.setModifiedAt(Time.utcNow());
    }
}
