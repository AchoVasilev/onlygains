package com.project.domain.valueobjects

import jakarta.persistence.Embeddable

@Embeddable
class FullName {
    var firstName: String? = null
        private set
    var lastName: String? = null
        private set

    private constructor(firstName: String, lastName: String) {
        this.firstName = firstName
        this.lastName = lastName
    }

    protected constructor()

    val fullName: String
        get() = String.format("%s %s", this.firstName, this.lastName)

    companion object {
        fun from(firstName: String, lastName: String): FullName {
            return FullName(firstName, lastName)
        }
    }
}
