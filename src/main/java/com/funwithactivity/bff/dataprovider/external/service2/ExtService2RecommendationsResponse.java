package com.funwithactivity.bff.dataprovider.external.service2;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonKey;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonIgnoreProperties(ignoreUnknown = false)
public class ExtService2RecommendationsResponse {

    private int errorCode;

    private String errorMessage;


    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
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
