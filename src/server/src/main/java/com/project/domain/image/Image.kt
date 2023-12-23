package com.project.domain.image

import com.project.domain.BaseEntity
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import java.util.UUID

@MappedSuperclass
abstract class Image protected constructor() : BaseEntity() {
    @Id
    val id: UUID = UUID.randomUUID()
    var url: String? = null
        private set


    protected constructor(url: String?) : this() {
        this.url = url
    }
}
