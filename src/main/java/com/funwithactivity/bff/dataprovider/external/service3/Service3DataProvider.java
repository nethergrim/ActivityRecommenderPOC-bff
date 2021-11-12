package com.funwithactivity.bff.dataprovider.external.service3;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.funwithactivity.bff.dataprovider.external.RequestMapper;
import com.funwithactivity.bff.dataprovider.external.SimpleResponseTranslator;
import com.funwithactivity.bff.dataprovider.external.abstraction.ErrorResponse;
import com.funwithactivity.bff.dataprovider.external.abstraction.RecommendationResponse;
import com.funwithactivity.bff.dataprovider.external.service2.request.ExtService2RecommendationRequest;
import com.funwithactivity.bff.dataprovider.external.service3.response.Service3RecommendationResponse;
import com.funwithactivity.bff.dataprovider.external.service3.response.Service3RecommendationsError;
import com.funwithactivity.bff.models.MeasurementUnit;
import com.funwithactivity.bff.utils.Measurement;
import okhttp3.OkHttpClient;

public class Service3DataProvider extends SimpleResponseTranslator {

    public static final String SERVICE_BASE_URL = "http://ase.asmt.live:8000/services/service3";
    public static final MeasurementUnit DEFAULT_MEASUREMENT_UNIT = MeasurementUnit.US;
    private static final RequestMapper REQUEST_MAPPER = req -> new ExtService2RecommendationRequest(Measurement.updateRequestMeasurementUnit(
            DEFAULT_MEASUREMENT_UNIT, req
    ));

    public Service3DataProvider(OkHttpClient client) {
        super(client);
    }

    @Override
    public RequestMapper getRequestMapper() {
        return REQUEST_MAPPER;
    }

    @Override
    public String getServiceUrl() {
        return SERVICE_BASE_URL;
    }

    @Override
    public boolean shouldRetryOnErrorCode(int errorCode) {
        return errorCode != 13;
    }

    @Override
    public ErrorResponse bodyToErrorResponse(String responseBody) throws JsonProcessingException {
        return mapper.readValue(responseBody, Service3RecommendationsError.class);
    }

    @Override
    public RecommendationResponse[] bodyToResponse(String responseBody) throws JsonProcessingException {
        return mapper.readValue(responseBody, Service3RecommendationResponse[].class);
    }
}
