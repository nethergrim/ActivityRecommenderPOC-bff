package com.funwithactivity.bff.dataprovider.external.service1;

import com.funwithactivity.bff.dataprovider.RecommendationsDataProvider;
import com.funwithactivity.bff.models.RecommendationRequest;
import com.funwithactivity.bff.models.RecommendationsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;


public class ExtService1DataProvider implements RecommendationsDataProvider {

    public static final String SERVICE_BASE_URL = "http://ase.asmt.live:8000/services/service1";

    RestTemplate restTemplate = new RestTemplate();

    @Override
    @NonNull
    public RecommendationsResponse provideRecommendations(RecommendationRequest request) {
        ExtService1RecommendationRequest request1 = new ExtService1RecommendationRequest(50, 50, "service1-dev");
        ExtService1RecommendationsResponse.Service1Recommendation[] recommendations = restTemplate.postForObject(SERVICE_BASE_URL, request1, ExtService1RecommendationsResponse.Service1Recommendation[].class);
        System.out.println(Arrays.toString(recommendations));
        return null;
    }

//    [Service1Recommendation{confidence=0.56, recommendation='Don't eat carbs!'}, Service1Recommendation{confidence=0.24, recommendation='Don't eat carbs!'}, Service1Recommendation{confidence=0.23, recommendation='Drink more still water'}, Service1Recommendation{confidence=0.16, recommendation='Drink more still water'}, Service1Recommendation{confidence=0.8, recommendation='Walk more'}, Service1Recommendation{confidence=0.22, recommendation='Focus on cycle exercises'}, Service1Recommendation{confidence=0.08, recommendation='Go for a physical check up'}, Service1Recommendation{confidence=0.06, recommendation='Time to stand up'}, Service1Recommendation{confidence=0.62, recommendation='Go for a physical check up'}]

}
