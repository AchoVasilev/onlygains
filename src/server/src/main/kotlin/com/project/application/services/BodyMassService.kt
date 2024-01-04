package com.project.application.services

import com.project.application.models.user.workout.CreateBmrResource
import com.project.application.models.user.workout.WorkoutProfileDetailsResource
import io.micronaut.transaction.annotation.Transactional
import jakarta.inject.Singleton
import java.util.UUID

@Singleton
open class BodyMassService(
    private val workoutProfileService: WorkoutProfileService
) {

    @Transactional
    open fun calculateBmr(profileId: UUID, bmrResource: CreateBmrResource): WorkoutProfileDetailsResource {

        var profile = this.workoutProfileService.getMockProfile()
        profile.updateIfNeeded(bmrResource)
        profile.calculateBmr(bmrResource.bmrEquation)

        profile = this.workoutProfileService.saveProfile(profile)

        return WorkoutProfileDetailsResource.from(profile)
    }

    @Transactional
    open fun calculateBmi(profileId: UUID): WorkoutProfileDetailsResource {
        var profile = this.workoutProfileService.getMockProfile()
        profile.calculateBmi()

        profile = this.workoutProfileService.saveProfile(profile)

        return WorkoutProfileDetailsResource.from(profile)
    }
}