package com.funwithactivity.bff.models;

import org.springframework.web.bind.annotation.RequestParam;

public class RecommendationRequest {
    private final String measurementSystem;
    private final float bodyWeight;
    private final float bodyHeight;

    public String getMeasurementSystem() {
        return measurementSystem;
    }

    public float getBodyWeight() {
        return bodyWeight;
    }

    public float getBodyHeight() {
        return bodyHeight;
    }

    public RecommendationRequest(String measurementSystem, float bodyWeight, float bodyHeight) {
        this.measurementSystem = measurementSystem;
        this.bodyWeight = bodyWeight;
        this.bodyHeight = bodyHeight;
    }


    @Override
    public String toString() {
        return "RecommendationRequest{" +
                "measurementSystem='" + measurementSystem + '\'' +
                ", bodyWeight='" + bodyWeight + '\'' +
                ", bodyHeight='" + bodyHeight + '\'' +
                '}';
    }
}
