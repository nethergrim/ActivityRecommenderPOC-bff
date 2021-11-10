package com.funwithactivity.bff.dataprovider.external.service1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = false)
public class ExtService1RecommendationsResponse {

    private int errorCode;
    private String errorMessage;

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    @JsonIgnoreProperties(ignoreUnknown = false)
    public static class Service1Recommendation {
        private float confidence;
        private String recommendation;

        public float getConfidence() {
            return confidence;
        }

        public String getRecommendation() {
            return recommendation;
        }

        @Override
        public String toString() {
            return "Service1Recommendation{" +
                    "confidence=" + confidence +
                    ", recommendation='" + recommendation + '\'' +
                    '}';
        }
    }
}