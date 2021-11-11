package com.funwithactivity.bff.dataprovider.external;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.funwithactivity.bff.dataprovider.RecommendationsDataProvider;
import com.funwithactivity.bff.dataprovider.external.abstraction.ErrorResponse;
import com.funwithactivity.bff.dataprovider.external.abstraction.RecommendationResponse;
import com.funwithactivity.bff.models.Recommendation;
import com.funwithactivity.bff.models.RecommendationRequest;
import com.funwithactivity.bff.models.RecommendationsResponse;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public abstract class SimpleResponseTranslator implements RecommendationsDataProvider {

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");
    protected final OkHttpClient client;
    protected final ObjectMapper mapper = new ObjectMapper();

    protected SimpleResponseTranslator(OkHttpClient client) {
        this.client = client;
    }


    protected RecommendationsResponse translateResponse(
            @Nullable RecommendationResponse[] response,
            @Nullable ErrorResponse errorResponse
    ) {
        if (errorResponse != null) {
            return new RecommendationsResponse(null, errorResponse.getError(), errorResponse.getErrorCode());
        } else if (response != null) {
            Recommendation[] recommendations = Arrays.stream(response).map(RecommendationResponse::toRecommendation).toArray(Recommendation[]::new);
            return new RecommendationsResponse(recommendations, null, 0);
        }
        throw new IllegalStateException("Both types of response are null");
    }

    @SuppressWarnings("deprecation")
    @Override
    @NonNull
    public RecommendationsResponse provideRecommendations(@NotNull RecommendationRequest req) throws IOException {
        RequestBody body = RequestBody.create(JSON, mapper.writeValueAsString(getRequestMapper().map(req)));

        Request request = new Request.Builder()
                .url(getServiceUrl())
                .post(body)
                .build();
        Response response = this.client.newCall(request).execute();
        ResponseBody responseBody = response.body();

        String responseBodyString = Objects.requireNonNull(responseBody).string();

        responseBody.close();
        RecommendationResponse[] recommendations;

        if (response.isSuccessful()) {
            try {
                recommendations = this.bodyToResponse(responseBodyString);
                return translateResponse(recommendations, null);
            } catch (MismatchedInputException e) {
                //
            }
        }

        ErrorResponse errorResponse = this.bodyToErrorResponse(responseBodyString);
        System.out.println(errorResponse.getError());
        System.out.println(errorResponse.getErrorCode());
        if (shouldRetryOnErrorCode(errorResponse.getErrorCode())) {
            return provideRecommendations(req);
        }

        return translateResponse(null, errorResponse);
    }


    public abstract RequestMapper getRequestMapper();

    public abstract String getServiceUrl();

    public abstract boolean shouldRetryOnErrorCode(int errorCode);

    public abstract ErrorResponse bodyToErrorResponse(String responseBody) throws JsonProcessingException;

    public abstract RecommendationResponse[] bodyToResponse(String responseBody) throws JsonProcessingException;

}
