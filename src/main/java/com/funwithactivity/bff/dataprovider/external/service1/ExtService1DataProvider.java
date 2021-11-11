package com.funwithactivity.bff.dataprovider.external.service1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.funwithactivity.bff.dataprovider.RecommendationsDataProvider;
import com.funwithactivity.bff.dataprovider.external.RequestMapper;
import com.funwithactivity.bff.models.MeasurementUnit;
import com.funwithactivity.bff.models.Recommendation;
import com.funwithactivity.bff.models.RecommendationRequest;
import com.funwithactivity.bff.models.RecommendationsResponse;
import com.funwithactivity.bff.utils.Measurement;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;


public class ExtService1DataProvider implements RecommendationsDataProvider {

    public static final String SERVICE_BASE_URL = "http://ase.asmt.live:8000/services/service1";
    public static final MeasurementUnit DEFAULT_MEASUREMENT_UNIT = MeasurementUnit.METRIC;
    private static final RequestMapper REQUEST_MAPPER = req -> new ExtService1RecommendationRequest(Measurement.mapRequestToDifferentMeasurementUnit(
            DEFAULT_MEASUREMENT_UNIT, req
    ));
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");
    private final OkHttpClient client;
    private final ObjectMapper mapper = new ObjectMapper();

    public ExtService1DataProvider(OkHttpClient restTemplate) {
        this.client = restTemplate;
    }

    private static RecommendationsResponse translateSuccessfulResponse(
            @Nullable ExtService1RecommendationsResponse.Service1Recommendation[] response
    ) {
        if (response == null || response.length == 0) {
            return new RecommendationsResponse(new Recommendation[0], null, 0);
        }
        Stream<Recommendation> recommendationStream = Arrays
                .stream(response)
                .map(service1Recommendation ->
                        new Recommendation(
                                (int) (service1Recommendation.getConfidence() * 1000),
                                service1Recommendation.getRecommendation(),
                                null)
                );
        return new RecommendationsResponse(recommendationStream.toArray(Recommendation[]::new), null, 0);
    }

    @Override
    @NonNull
    public RecommendationsResponse provideRecommendations(@NotNull RecommendationRequest req) throws IOException {
        RequestBody body = RequestBody.create(JSON, mapper.writeValueAsString(REQUEST_MAPPER.map(req)));

        Request request = new Request.Builder()
                .url(SERVICE_BASE_URL)
                .post(body)
                .build();
        Response response = this.client.newCall(request).execute();
        ResponseBody responseBody = response.body();

        String responseBodyString = Objects.requireNonNull(responseBody).string();
        responseBody.close();
        if (response.isSuccessful()) {
            try {
                ExtService1RecommendationsResponse.Service1Recommendation[] recommendations = mapper.readValue(responseBodyString, ExtService1RecommendationsResponse.Service1Recommendation[].class);
                return translateSuccessfulResponse(recommendations);
            } catch (MismatchedInputException e) {
                //
            }
        }

        ExtService1RecommendationsResponse errorResponse = mapper.readValue(responseBodyString, ExtService1RecommendationsResponse.class);
        if (errorResponse.getErrorCode() != 13) {
            return provideRecommendations(req);
        }
        return new RecommendationsResponse(null, errorResponse.getErrorMessage(), errorResponse.getErrorCode());
    }
}
