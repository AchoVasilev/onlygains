package com.project.domain.valueobjects

import com.project.common.enums.WeightType
import jakarta.persistence.Embeddable
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated

@Embeddable
class Weight {
    var weight: Double = 0.0
        private set

    @Enumerated(EnumType.STRING)
    var weightType: WeightType? = null
        private set

    private constructor(weight: Double) {
        this.weight = weight
        this.weightType = WeightType.KG
    }

    protected constructor()

    companion object {
        fun from(weight: Double): Weight {
            return Weight(weight)
        }
    }
}
