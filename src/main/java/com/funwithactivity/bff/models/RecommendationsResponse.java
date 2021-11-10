package com.funwithactivity.bff.models;

import java.util.Arrays;

public class RecommendationsResponse {

    private final Recommendation[] recommendations;
    private final String error;

    public RecommendationsResponse(Recommendation[] recommendations, String error) {
        this.recommendations = recommendations;
        this.error = error;
    }

    public Recommendation[] getRecommendations() {
        return recommendations;
    }

    public String getError() {
        return error;
    }

    @Override
    public String toString() {
        return "RecommendationsResponse{" +
                "recommendations=" + Arrays.toString(recommendations) +
                ", error='" + error + '\'' +
                '}';
    }
}
