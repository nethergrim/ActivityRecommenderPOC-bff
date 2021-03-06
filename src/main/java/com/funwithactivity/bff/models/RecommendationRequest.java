package com.funwithactivity.bff.models;

public class RecommendationRequest {
    private final String measurementSystem;
    private final float bodyWeight;
    private final float bodyHeight;
    private final String birthdate;

    public RecommendationRequest(String measurementSystem, float bodyWeight, float bodyHeight, String birthdate) {
        this.measurementSystem = measurementSystem;
        this.bodyWeight = bodyWeight;
        this.bodyHeight = bodyHeight;
        this.birthdate = birthdate;
    }

    public String getMeasurementSystem() {
        return measurementSystem;
    }

    public float getBodyWeight() {
        return bodyWeight;
    }

    public float getBodyHeight() {
        return bodyHeight;
    }

    public String getBirthdate() {
        return birthdate;
    }

    @Override
    public String toString() {
        return "RecommendationRequest{" +
                "measurementSystem='" + measurementSystem + '\'' +
                ", bodyWeight=" + bodyWeight +
                ", bodyHeight=" + bodyHeight +
                ", birthdate='" + birthdate + '\'' +
                '}';
    }
}
