package com.project.authentication

import com.project.infrastructure.data.UserRepository
import jakarta.inject.Singleton

@Singleton
open class UserService(private val userRepository: UserRepository) {

}