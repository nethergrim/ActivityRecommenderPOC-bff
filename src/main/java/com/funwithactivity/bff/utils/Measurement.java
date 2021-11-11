package com.funwithactivity.bff.utils;

import com.funwithactivity.bff.models.MeasurementUnit;
import com.funwithactivity.bff.models.RecommendationRequest;
import org.springframework.lang.NonNull;

public class Measurement {

    public static RecommendationRequest updateRequestMeasurementUnit(
            @NonNull MeasurementUnit targetMeasurementUnit,
            @NonNull RecommendationRequest req
    ) {
        if (targetMeasurementUnit.getValue().equalsIgnoreCase(req.getMeasurementSystem())) {
            return req;
        }
        switch (targetMeasurementUnit) {
            case US:
                return new RecommendationRequest(targetMeasurementUnit.getValue(), convertKGToPounds(req.getBodyWeight()), convertCMToFeet(req.getBodyHeight()), req.getBirthdate());
            case METRIC:
            default:
                return new RecommendationRequest(targetMeasurementUnit.getValue(), convertPoundsToKG(req.getBodyWeight()), convertFeetToCM(req.getBodyHeight()), req.getBirthdate());
        }
    }

    public static float convertCMToFeet(float cm) {
        return (float) (cm / 0.03280839895);
    }

    public static float convertFeetToCM(float feet) {
        return (float) (feet * 30.48);
    }

    public static float convertPoundsToKG(float lb) {
        return (float) (lb * 0.45359237);
    }

    public static float convertKGToPounds(float kg) {
        return (float) (kg / 0.45359237);
    }
}
