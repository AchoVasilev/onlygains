package com.project.domain.workout

import com.project.common.Constants.DATE_FORMAT
import com.project.domain.BaseEntity
import com.project.utilities.DateTimeFormatterUtil.formatterFrom
import com.project.utilities.Time.utcNow
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import java.util.UUID

@Entity(name = "workout_templates")
class WorkoutTemplate() : BaseEntity() {
    @Id
    val id: UUID = UUID.randomUUID()

    var name: String?
        private set

    var originalWorkoutTemplateId: UUID? = null
        private set

    @OneToMany(mappedBy = "workoutTemplate")
    val exercises: MutableList<WorkoutExercise?> = mutableListOf()

    init {
        this.name = createdAt.format(formatterFrom(DATE_FORMAT))
    }

    protected constructor(template: OriginalWorkoutTemplate) : this() {
        this.name = template.name
        this.originalWorkoutTemplateId = template.id
        exercises.addAll(template.exercises)
    }

    fun addExercise(exercise: WorkoutExercise) {
        exercise.workoutTemplate = this
        exercises.add(exercise)
        this.modifiedAt = utcNow()
    }

    companion object {
        @JvmStatic
        fun from(originalWorkoutTemplate: OriginalWorkoutTemplate): WorkoutTemplate {
            return WorkoutTemplate(originalWorkoutTemplate)
        }

        @JvmStatic
        fun basicTemplate(): WorkoutTemplate {
            return WorkoutTemplate()
        }
    }
}
