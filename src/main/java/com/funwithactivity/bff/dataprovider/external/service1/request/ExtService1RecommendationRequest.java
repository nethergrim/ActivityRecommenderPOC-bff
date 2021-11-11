package com.funwithactivity.bff.dataprovider.external.service1.request;

import com.funwithactivity.bff.models.RecommendationRequest;

public class ExtService1RecommendationRequest {

    private static final String TOKEN = "service1-dev";
    private final float height;
    private final float weight;
    private final String token;

    public ExtService1RecommendationRequest(RecommendationRequest anotherRequest) {
        this.height = anotherRequest.getBodyHeight();
        this.weight = anotherRequest.getBodyWeight();
        this.token = TOKEN;
    }

    public ExtService1RecommendationRequest(float height, float weight, String token) {
        this.height = height;
        this.weight = weight;
        this.token = token;
    }

    public float getHeight() {
        return height;
    }

    public float getWeight() {
        return weight;
    }

    public String getToken() {
        return token;
    }


}
