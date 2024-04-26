package com.project.domain.workout

import com.project.common.BaseEntity
import com.project.infrastructure.converters.DurationToIntervalConverter
import com.project.utilities.FindUtil.findByProperty
import com.project.utilities.Time.utcNow
import jakarta.persistence.Convert
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import java.time.Duration
import java.time.Instant
import java.util.UUID

@Entity(name = "workouts")
class Workout protected constructor() : BaseEntity() {
    @Id
    val id: UUID = UUID.randomUUID()

    @OneToOne
    @JoinColumn(name = "workout_template_id")
    var workoutTemplate: WorkoutTemplate? = null

    var finishedAt: Instant? = null
        private set

    @Convert(converter = DurationToIntervalConverter::class)
    var duration: Duration? = null
        private set

    @Enumerated(EnumType.STRING)
    var status: WorkoutStatus
        private set

    init {
        this.status = WorkoutStatus.Started
    }

    fun finish() {
        this.status = WorkoutStatus.Finished
        this.finishedAt = utcNow()
        this.setDuration()
        this.modifiedAt = utcNow()
    }

    fun cancel() {
        this.status = WorkoutStatus.Cancelled
        this.modifiedAt = utcNow()
    }

    fun addExercise(exercise: WorkoutExercise) {
        if (this.workoutTemplate == null) {
            this.workoutTemplate = WorkoutTemplate()
        }

        workoutTemplate!!.addExercise(exercise)
        this.modifiedAt = utcNow()
    }

    fun addSetToExercise(exerciseId: UUID, weight: Double, repetitions: Int) {
        val exercise = this.findExerciseBy(exerciseId)
        exercise?.addSet(repetitions, weight)
        this.modifiedAt = utcNow()
    }

    fun updateExercise(exerciseId: UUID, setId: UUID, weight: Double, repetitions: Int) {
        val exercise = this.findExerciseBy(exerciseId)
        exercise?.updateSet(setId, weight, repetitions)

        this.modifiedAt = utcNow()
    }

    private fun findExerciseBy(exerciseId: UUID): WorkoutExercise? {
        val predicate = {e: WorkoutExercise? -> e?.exerciseId == exerciseId }
        return findByProperty(workoutTemplate?.exercises, predicate)
    }

    private fun setDuration() {
        this.duration = Duration.between(this.createdAt, this.finishedAt)
    }

    companion object {
        @JvmStatic
        fun start(workoutTemplate: WorkoutTemplate): Workout {
            val workout = Workout()
            workout.workoutTemplate = workoutTemplate
            return workout
        }

        @JvmStatic
        fun startEmptyWorkout(): Workout {
            return Workout()
        }
    }
}
