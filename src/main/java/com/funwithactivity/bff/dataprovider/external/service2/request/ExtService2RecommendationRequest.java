package com.funwithactivity.bff.dataprovider.external.service2.request;

import com.funwithactivity.bff.models.RecommendationRequest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ExtService2RecommendationRequest {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter
            .ofPattern("M/d/yyyy");
    private final Measurements measurements;
    private final String session_token;
    private final long birth_date;

    public ExtService2RecommendationRequest(RecommendationRequest anotherRequest) {
        this.session_token = java.util.UUID.randomUUID().toString();
        this.measurements = new Measurements(anotherRequest.getBodyWeight(), anotherRequest.getBodyHeight());

        String dateString = anotherRequest.getBirthdate();
        if (dateString != null) {
            LocalDate date = LocalDate.parse(dateString, DATE_TIME_FORMATTER);
            this.birth_date = date.toEpochDay() * 24 * 60 * 60;
        } else {
            this.birth_date = 0;
        }
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