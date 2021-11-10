package com.funwithactivity.bff.dataprovider;

import com.funwithactivity.bff.models.RecommendationRequest;
import com.funwithactivity.bff.models.RecommendationsResponse;
import org.springframework.lang.NonNull;

import java.util.List;

public class CombinedRecommendationsDataProvider implements RecommendationsDataProvider{

    private final List<RecommendationsDataProvider> downstreamDataProviders;

    public CombinedRecommendationsDataProvider(List<RecommendationsDataProvider> downstreamDataProviders) {
        this.downstreamDataProviders = downstreamDataProviders;
    }

    @Override
    @NonNull
    public RecommendationsResponse provideRecommendations(RecommendationRequest request) {
        return new RecommendationsResponse(null, "test error");
    }
}
