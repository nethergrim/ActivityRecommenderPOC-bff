package com.funwithactivity.bff.models;

import java.util.Arrays;

public class RecommendationsResponse {

    private final Recommendation[] recommendations;
    private final String error;
    private final int errorCode;

    public RecommendationsResponse(Recommendation[] recommendations, String error, int errorCode) {
        this.recommendations = recommendations;
        this.error = error;
        this.errorCode = errorCode;
    }

    public Recommendation[] getRecommendations() {
        return recommendations;
    }

    public String getError() {
        return error;
    }

    public int getErrorCode() {
        return errorCode;
    }

    @Override
    public String toString() {
        return "RecommendationsResponse{" +
                "recommendations=" + Arrays.toString(recommendations) +
                ", error='" + error + '\'' +
                ", errorCode=" + errorCode +
                '}';
    }
}
