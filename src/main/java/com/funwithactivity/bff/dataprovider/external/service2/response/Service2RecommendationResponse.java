package com.funwithactivity.bff.dataprovider.external.service2.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.funwithactivity.bff.dataprovider.external.abstraction.RecommendationResponse;
import com.funwithactivity.bff.models.Recommendation;

@JsonIgnoreProperties(ignoreUnknown = false)
public class Service2RecommendationResponse implements RecommendationResponse {
    private int priority;
    private String title;
    private String details;

    public int getPriority() {
        return priority;
    }

    public String getTitle() {
        return title;
    }

    public String getDetails() {
        return details;
    }

    @Override
    public String toString() {
        return "Service2Recommendation{" +
                "priority=" + priority +
                ", title='" + title + '\'' +
                ", details='" + details + '\'' +
                '}';
    }

    @Override
    public Recommendation toRecommendation() {
        return new Recommendation(priority, title, details);
    }
}
