package com.project.common.enums

import com.fasterxml.jackson.annotation.JsonValue

enum class WeightType {
    @JsonValue
    KG,
    @JsonValue
    LB
}
