package com.project.application.services

import com.project.application.models.user.workout.CreateBmrResource
import com.project.application.models.user.workout.WorkoutProfileDetailsResource
import com.project.infrastructure.utilities.LoggerProvider
import io.micronaut.transaction.annotation.Transactional
import jakarta.inject.Singleton
import java.util.UUID

@Singleton
open class BodyMassService(
    private val workoutProfileService: WorkoutProfileService
) {
    @Transactional
    open fun calculateBmr(profileId: UUID, bmrResource: CreateBmrResource): WorkoutProfileDetailsResource {
        log.info("Calculating BMR. [profileId={}]", profileId)
        var profile = this.workoutProfileService.getMockProfile()
        profile.calculateBmr(bmrResource.bmrEquation, bmrResource.activityLevel)

        profile = this.workoutProfileService.saveProfile(profile)

        return WorkoutProfileDetailsResource.from(profile)
    }

    @Transactional
    open fun calculateBmi(profileId: UUID): WorkoutProfileDetailsResource {
        log.info("Calculating BMI. [profileId]={}", profileId)
        var profile = this.workoutProfileService.getMockProfile()

        profile.calculateBmi()

        profile = this.workoutProfileService.saveProfile(profile)

        return WorkoutProfileDetailsResource.from(profile)
    }

    companion object {
        @JvmStatic
        val log = LoggerProvider.getLogger(BodyMassService::class.java)
    }
}