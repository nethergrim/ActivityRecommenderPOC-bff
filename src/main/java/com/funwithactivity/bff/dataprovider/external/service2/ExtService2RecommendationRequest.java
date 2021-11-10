package com.funwithactivity.bff.dataprovider.external.service2;

import com.funwithactivity.bff.models.RecommendationRequest;

public class ExtService2RecommendationRequest {

    private static final String TOKEN = "service1-dev";
    private final Measurements measurements;
    private final String session_token;
    private final long birth_date;

    public ExtService2RecommendationRequest(RecommendationRequest anotherRequest) {
        this.session_token = java.util.UUID.randomUUID().toString();
        this.measurements = new Measurements(anotherRequest.getBodyWeight(), anotherRequest.getBodyHeight());
        this.birth_date = 1615876858; // TODO replace with actual user's DOB
    }

    public ExtService2RecommendationRequest(Measurements measurements, String session, long birthDate) {
        this.measurements = measurements;
        this.session_token = session;
        this.birth_date = birthDate;
    }

    public Measurements getMeasurements() {
        return measurements;
    }

    public String getSession_token() {
        return session_token;
    }

    public long getBirth_date() {
        return birth_date;
    }

    public static class Measurements {
        private final float mass;
        private final float height;

        public Measurements(float mass, float height) {
            this.mass = mass;
            this.height = height;
        }

        public float getMass() {
            return mass;
        }

        public float getHeight() {
            return height;
        }
    }

}