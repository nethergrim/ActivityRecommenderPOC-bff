package com.funwithactivity.bff.dataprovider.external.service2.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.funwithactivity.bff.dataprovider.external.abstraction.ErrorResponse;

@JsonIgnoreProperties(ignoreUnknown = false)
public class Service2RecommendationsError implements ErrorResponse {

    private int errorCode;

    private String errorMessage;

    @Override
    public String getError() {
        return errorMessage;
    }

    @Override
    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
