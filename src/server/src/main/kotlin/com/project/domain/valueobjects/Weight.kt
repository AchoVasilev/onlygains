package com.project.domain.valueobjects

import com.project.common.enums.WeightType
import jakarta.persistence.Embeddable
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated

@Embeddable
class Weight {
    var weight: Double? = null
        private set

    @Enumerated(EnumType.STRING)
    val weightType: WeightType = WeightType.KG

    constructor(weight: Double) {
        this.weight = weight
    }

    protected constructor()
}
