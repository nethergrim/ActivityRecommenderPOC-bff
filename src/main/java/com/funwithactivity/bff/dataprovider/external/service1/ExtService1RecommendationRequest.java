package com.funwithactivity.bff.dataprovider.external.service1;

public class ExtService1RecommendationRequest {
    private final float height;
    private final float weight;
    private final String token;

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
