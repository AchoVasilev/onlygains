package com.project.application.services

import com.project.application.models.user.workout.WorkoutProfileDetailsResource
import com.project.domain.user.workout.WorkoutProfile
import com.project.infrastructure.data.WorkoutProfileRepository
import io.micronaut.transaction.annotation.Transactional
import jakarta.inject.Singleton
import java.util.UUID

@Singleton
open class WorkoutProfileService(private val workoutProfileRepository: WorkoutProfileRepository) {
    //TODO: rework
    @Transactional
    open fun getBy(userId: UUID): WorkoutProfileDetailsResource {
        var profile = this.createProfile()
        profile = this.saveProfile(profile)

        return WorkoutProfileDetailsResource.from(profile)
    }

    //TODO: remove
    @Transactional
    open fun getMockProfile(): WorkoutProfile {
        val profile = this.createProfile()
        return this.saveProfile(profile)
    }

    private fun createProfile(): WorkoutProfile {
        val profile = WorkoutProfile(UUID.randomUUID())
        return profile
    }

    open fun saveProfile(profile: WorkoutProfile): WorkoutProfile {
        return this.workoutProfileRepository.save(profile)
    }
}