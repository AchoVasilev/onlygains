package com.project.common.errormessages

class UserMessages {
    companion object {
        val AUTHENTICATION_FAILED = Message("user.errors.auth", "Cannot authenticate user")
        val CREDENTIALS_NOT_MATCH = Message("user.errors.auth", "Credentials do not match")
        val USER_DEACTIVATED = Message("user.errors.status", "User is deactivated")
        val USER_NOT_EXIST = Message("user.errors", "User does not exist")
        val USER_EXISTS = Message("user.errors", "User exists")
        val PASSWORDS_NOT_MATCH = Message("user.errors", "Passwords do not match")
    }
}