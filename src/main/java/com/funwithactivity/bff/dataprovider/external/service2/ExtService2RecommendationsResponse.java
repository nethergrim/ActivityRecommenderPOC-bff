package com.funwithactivity.bff.dataprovider.external.service2;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = false)
public class ExtService2RecommendationsResponse {

    private Service2Recommendation[] recommendations;
    private int code;
    private String error;

    public Service2Recommendation[] getRecommendations() {
        return recommendations;
    }

    public int getCode() {
        return code;
    }

    public int getErrorCode() {
        return code;
    }

    public String getError() {
        return error;
    }

    @JsonIgnoreProperties(ignoreUnknown = false)
    public static class Service2Recommendation {
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
    }
}
