package com.project.domain.user

import com.project.common.BaseEntity
import com.project.domain.valueobjects.FullName
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import java.util.UUID

@Entity(name = "users")
class User protected constructor() : BaseEntity() {
    @Id
    val id: UUID = UUID.randomUUID()
    var email: String? = null
        private set
    var password: String? = null
        private set

    @Embedded
    private var fullName: FullName? = null

    @ManyToOne
    var role: Role? = null
        private set

    var imageUrl: String? = null

    var workoutProfileId: UUID? = null

    val firstName: String?
        get() = fullName!!.firstName

    val lastName: String?
        get() = fullName!!.lastName

    constructor(email: String, password: String, firstName: String, lastName: String, role: Role) : this() {
        this.email = email
        this.password = password
        this.fullName = FullName.from(firstName, lastName)
        this.role = role
    }

    fun getFullName(): String {
        return fullName!!.fullName
    }
}
