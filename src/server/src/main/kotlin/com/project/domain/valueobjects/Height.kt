package com.project.domain.valueobjects

import jakarta.persistence.Embeddable

@Embeddable
class Height {
    var height: Double = 0.0
        private set

    val heightType: String = "cm"

    private constructor(height: Double) {
        this.height = height
    }

    protected constructor()

    companion object {
        @JvmStatic
        fun from(height: Double) : Height {
            return Height(height)
        }
    }
}