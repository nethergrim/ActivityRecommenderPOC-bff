package com.funwithactivity.bff.dataprovider.external.service1;

import com.funwithactivity.bff.dataprovider.RecommendationsDataProvider;
import com.funwithactivity.bff.dataprovider.external.RequestMapper;
import com.funwithactivity.bff.models.MeasurementUnit;
import com.funwithactivity.bff.models.Recommendation;
import com.funwithactivity.bff.models.RecommendationRequest;
import com.funwithactivity.bff.models.RecommendationsResponse;
import com.funwithactivity.bff.utils.Measurement;
import org.springframework.lang.NonNull;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.stream.Stream;


public class ExtService1DataProvider implements RecommendationsDataProvider {

    public static final String SERVICE_BASE_URL = "http://ase.asmt.live:8000/services/service1";
    public static final MeasurementUnit DEFAULT_MEASUREMENT_UNIT = MeasurementUnit.METRIC;
    private static final RequestMapper REQUEST_MAPPER = req -> new ExtService1RecommendationRequest(Measurement.mapRequestToDifferentMeasurementUnit(
            DEFAULT_MEASUREMENT_UNIT, req
    ));
    private final RestTemplate restTemplate;

    public ExtService1DataProvider(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private static RecommendationsResponse translateResponse(ExtService1RecommendationsResponse.Service1Recommendation[] response) {
        if (response.length == 0) {
            return new RecommendationsResponse(new Recommendation[0], null);
        }
        Stream<Recommendation> recommendationStream = Arrays.stream(response).map(service1Recommendation -> new Recommendation((int) (service1Recommendation.getConfidence() * 1000), service1Recommendation.getRecommendation(), null));

        return new RecommendationsResponse(recommendationStream.toArray(Recommendation[]::new), null); // TODO map error
    }

    @Override
    @NonNull
    public RecommendationsResponse provideRecommendations(RecommendationRequest req) {
        ExtService1RecommendationsResponse.Service1Recommendation[] recommendations = restTemplate.postForObject(
                SERVICE_BASE_URL,
                REQUEST_MAPPER.map(req),
                ExtService1RecommendationsResponse.Service1Recommendation[].class);
        if (recommendations == null) {
            return new RecommendationsResponse(null, "Something went wrong");
        }
        return translateResponse(recommendations);
    }
}
