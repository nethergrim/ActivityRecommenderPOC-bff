package com.funwithactivity.bff.dataprovider.external.service2;

import com.funwithactivity.bff.dataprovider.RecommendationsDataProvider;
import com.funwithactivity.bff.models.RecommendationRequest;
import com.funwithactivity.bff.models.RecommendationsResponse;
import org.springframework.lang.NonNull;

public class ExtService2DataProvider implements RecommendationsDataProvider {
    @Override
    @NonNull
    public RecommendationsResponse provideRecommendations(RecommendationRequest request) {
        return null;
    }
}
