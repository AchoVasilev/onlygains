package com.project.application.services

import com.project.application.models.user.workout.UpdateWorkoutProfileResource
import com.project.application.models.user.workout.WorkoutProfileDetailsResource
import com.project.domain.user.workout.WorkoutProfile
import com.project.infrastructure.data.WorkoutProfileRepository
import com.project.infrastructure.exceptions.EntityNotFoundException
import io.micronaut.transaction.annotation.Transactional
import jakarta.inject.Singleton
import java.util.UUID

@Singleton
open class WorkoutProfileService(private val workoutProfileRepository: WorkoutProfileRepository) {
    //TODO: rework
    @Transactional
    open fun getBy(userId: UUID): WorkoutProfileDetailsResource {
        var profile: WorkoutProfile
        val all = this.workoutProfileRepository.findAll()
        if (all.isEmpty()) {
            profile = this.createProfile()
            profile = this.saveProfile(profile)
        } else {
            profile = all[0]
        }

        return WorkoutProfileDetailsResource.from(profile)
    }

    //TODO: remove
    @Transactional
    open fun getMockProfile(): WorkoutProfile {
        var profile: WorkoutProfile
        val all = this.workoutProfileRepository.findAll()
        if (all.isEmpty()) {
            profile = this.createProfile()
            profile = this.saveProfile(profile)
        } else {
            profile = all[0]
        }

        return profile
    }

    private fun createProfile(): WorkoutProfile {
        val profile = WorkoutProfile(UUID.randomUUID())
        return profile
    }

    @Transactional
    open fun saveProfile(profile: WorkoutProfile): WorkoutProfile {
        return this.workoutProfileRepository.save(profile)
    }

    @Transactional
    open fun update(profileId: UUID, resource: UpdateWorkoutProfileResource): WorkoutProfileDetailsResource? {
        var profile = this.getProfile(profileId)
        profile.updateIfNeeded(resource)
        profile = this.saveProfile(profile)

        return WorkoutProfileDetailsResource.from(profile)
    }

    private fun getProfile(id: UUID): WorkoutProfile {
        return this.workoutProfileRepository.findById(id)
            .orElseThrow { EntityNotFoundException(WorkoutProfile::class, id) }
    }
}