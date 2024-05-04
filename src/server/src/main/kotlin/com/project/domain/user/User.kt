package com.project.domain.user

import com.project.common.BaseEntity
import com.project.domain.valueobjects.FullName
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Id
import java.util.UUID

@Entity(name = "users")
class User protected constructor() : BaseEntity() {
    @Id
    val id: UUID = UUID.randomUUID()
    var email: String = ""
        private set
    var password: String = ""
        private set

    @Embedded
    private var fullName: FullName? = null

    @Enumerated(EnumType.STRING)
    var role: Role = Role.USER
        private set

    @Enumerated(EnumType.STRING)
    var status: UserStatus = UserStatus.ACTIVE

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
        this.status = UserStatus.ACTIVE
    }

    fun getFullName(): String {
        return fullName!!.fullName
    }
}