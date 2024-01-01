package com.project.application.services

import com.project.application.models.user.workout.BmrDetailsResource
import com.project.application.models.user.workout.CreateBmrResource
import com.project.domain.user.User
import com.project.domain.user.workout.BodyFat
import com.project.domain.user.workout.WorkoutProfile
import com.project.domain.valueobjects.Height
import com.project.domain.valueobjects.Weight
import com.project.infrastructure.data.RoleRepository
import jakarta.inject.Singleton
import java.util.UUID

@Singleton
open class BodyMassService(private val roleRepository: RoleRepository) {

    open fun calculateBmr(userId: UUID, bmrResource: CreateBmrResource) : BmrDetailsResource {

        //TODO: mock user till auth
        //TODO: get the profile from a repo
        val role = roleRepository.findById(UUID.fromString("ac3d9fd0-1725-466e-b0a7-00b9cd2161a7"))
        val user = User("email@abv.bg", "somepwd", "Gosho", "Peshev", role.get())
        user.imageUrl = "https://res.cloudinary.com/dpo3vbxnl/image/upload/v1691942376/onlygains/categories/hiking-trail-names_fgpox2.jpg"

        val profile = WorkoutProfile(user)
        this.updateProfile(profile, bmrResource)
        profile.calculateBmr(bmrResource.bmrEquation)

        return BmrDetailsResource(profile.bmr!!.calories!!, profile.bmr!!.unitType)
    }

    //TODO: get this out of here
    private fun updateProfile(profile: WorkoutProfile, resource: CreateBmrResource) {
        if (resource.bodyFat != null) profile.bodyFat = BodyFat(resource.bodyFat)
        if (resource.height != null) profile.height = Height(resource.height)
        if (resource.weight != null) profile.weight = Weight(resource.weight)
        if (resource.gender != null) profile.gender = resource.gender
        if (resource.age != null) profile.age = resource.age
    }
}