package com.funwithactivity.bff.dataprovider.external.service1.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.funwithactivity.bff.dataprovider.external.abstraction.RecommendationResponse;
import com.funwithactivity.bff.models.Recommendation;

@JsonIgnoreProperties
public class Service1RecommendationResponse implements RecommendationResponse {
    private float confidence;
    private String recommendation;

    public float getConfidence() {
        return confidence;
    }

    public String getRecommendation() {
        return recommendation;
    }

    @Override
    public Recommendation toRecommendation() {
        return new Recommendation((int) (confidence * 1000), recommendation, null);
    }

    @Override
    public String toString() {
        return "Service1Recommendation{" +
                "confidence=" + confidence +
                ", recommendation='" + recommendation + '\'' +
                '}';
    }
}
