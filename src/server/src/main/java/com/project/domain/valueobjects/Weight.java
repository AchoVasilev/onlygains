package com.project.domain.valueobjects;

import com.project.common.enums.WeightType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Embeddable
public class Weight {
    private double weight;
    @Enumerated(EnumType.STRING)
    private WeightType weightType;

    private Weight(double weight) {
        this.weight = weight;
        this.weightType = WeightType.KG;
    }

    protected Weight() {}

    public static Weight from(int weight) {
        return new Weight(weight);
    }

    public double getWeight() {
        return weight;
    }

    public WeightType getWeightType() {
        return weightType;
    }
}
