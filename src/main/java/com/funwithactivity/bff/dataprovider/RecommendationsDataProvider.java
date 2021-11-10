package com.funwithactivity.bff.dataprovider;

import com.funwithactivity.bff.models.RecommendationRequest;
import com.funwithactivity.bff.models.RecommendationsResponse;
import org.springframework.lang.NonNull;

import java.io.IOException;

public interface RecommendationsDataProvider {

    @NonNull
    RecommendationsResponse provideRecommendations(@NonNull RecommendationRequest request) throws IOException;
}
