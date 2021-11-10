package com.funwithactivity.bff.models;

import org.springframework.web.bind.annotation.RequestParam;

public class RecommendationRequest {
    private final String measurementSystem;
    private final String bodyWeight;
    private final String bodyHeight;

    public RecommendationRequest(String measurementSystem, String bodyWeight, String bodyHeight) {
        this.measurementSystem = measurementSystem;
        this.bodyWeight = bodyWeight;
        this.bodyHeight = bodyHeight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RecommendationRequest that = (RecommendationRequest) o;

        if (!measurementSystem.equals(that.measurementSystem)) return false;
        if (!bodyWeight.equals(that.bodyWeight)) return false;
        return bodyHeight.equals(that.bodyHeight);
    }

    @Override
    public int hashCode() {
        int result = measurementSystem.hashCode();
        result = 31 * result + bodyWeight.hashCode();
        result = 31 * result + bodyHeight.hashCode();
        return result;
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
