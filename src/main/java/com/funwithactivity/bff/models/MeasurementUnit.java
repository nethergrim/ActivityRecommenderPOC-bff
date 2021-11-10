package com.funwithactivity.bff.models;

public enum MeasurementUnit {
    METRIC("metric"),
    US("us");

    private final String value;

    MeasurementUnit(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
