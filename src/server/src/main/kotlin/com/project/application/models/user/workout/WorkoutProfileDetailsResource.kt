package com.project.application.models.user.workout

import com.project.domain.user.workout.WorkoutProfile
import com.project.domain.valueobjects.EntityId
import io.micronaut.serde.annotation.Serdeable
import java.util.UUID

@Serdeable
data class WorkoutProfileDetailsResource(
    val id: EntityId,
    val userId: UUID,
    val weight: WeightDetailsResource,
    val height: HeightDetailsResource,
    val bodyFat: BodyFatDetailsResource,
    val age: Int?,
    val gender: String?,
    val bmi: Double?,
    val bmr: BmrDetailsResource
) {
    companion object {
        @JvmStatic
        fun from(workoutProfile: WorkoutProfile): WorkoutProfileDetailsResource {
            return WorkoutProfileDetailsResource(
                workoutProfile.id,
                workoutProfile.userId!!,
                WeightDetailsResource(workoutProfile.weight?.weight, workoutProfile.weight?.weightType?.name),
                HeightDetailsResource(workoutProfile.height?.height, workoutProfile.height?.heightType),
                BodyFatDetailsResource(workoutProfile.bodyFat?.bodyFat, workoutProfile.bodyFat?.percent),
                workoutProfile.age,
                workoutProfile.gender?.name,
                workoutProfile.bmi?.bmi,
                BmrDetailsResource(workoutProfile.bmr?.calories, workoutProfile.bmr?.unitType)
            )
        }
    }
}
