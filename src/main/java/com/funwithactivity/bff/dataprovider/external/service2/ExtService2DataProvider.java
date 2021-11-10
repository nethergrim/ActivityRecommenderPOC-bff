package com.funwithactivity.bff.dataprovider.external.service2;

import com.funwithactivity.bff.dataprovider.RecommendationsDataProvider;
import com.funwithactivity.bff.dataprovider.external.RequestMapper;
import com.funwithactivity.bff.dataprovider.external.service1.ExtService1RecommendationRequest;
import com.funwithactivity.bff.dataprovider.external.service1.ExtService1RecommendationsResponse;
import com.funwithactivity.bff.models.MeasurementUnit;
import com.funwithactivity.bff.models.Recommendation;
import com.funwithactivity.bff.models.RecommendationRequest;
import com.funwithactivity.bff.models.RecommendationsResponse;
import com.funwithactivity.bff.utils.Measurement;
import org.springframework.lang.NonNull;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.stream.Stream;

public class ExtService2DataProvider implements RecommendationsDataProvider {

    public static final String SERVICE_BASE_URL = "http://ase.asmt.live:8000/services/service2";
    public static final MeasurementUnit DEFAULT_MEASUREMENT_UNIT = MeasurementUnit.US;
    private static final RequestMapper REQUEST_MAPPER = req -> new ExtService2RecommendationRequest(Measurement.mapRequestToDifferentMeasurementUnit(
            DEFAULT_MEASUREMENT_UNIT, req
    ));
    private final RestTemplate restTemplate;

    public ExtService2DataProvider(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    private static RecommendationsResponse translateResponse(ExtService2RecommendationsResponse.Service2Recommendation[] response) {
//        if (response.getError() != null) {
//            return new RecommendationsResponse(null, response.getError());
//        }
        if (response.length == 0) {
            return new RecommendationsResponse(new Recommendation[0], null);
        }

        Stream<Recommendation> recommendationStream = Arrays.stream(response).map(rec -> new Recommendation(rec.getPriority(), rec.getTitle(), rec.getDetails()));

        return new RecommendationsResponse(recommendationStream.toArray(Recommendation[]::new), null); // TODO map error
    }

    @Override
    @NonNull
    public RecommendationsResponse provideRecommendations(RecommendationRequest req) {
        ExtService2RecommendationsResponse.Service2Recommendation[] response = restTemplate.postForObject(
                SERVICE_BASE_URL,
                REQUEST_MAPPER.map(req),
                ExtService2RecommendationsResponse.Service2Recommendation[].class);
        if (response == null) {
            return new RecommendationsResponse(null, "Something went wrong");
        }
        return translateResponse(response);
    }
}
