package com.funwithactivity.bff.dataprovider;

import com.funwithactivity.bff.models.Recommendation;
import com.funwithactivity.bff.models.RecommendationRequest;
import com.funwithactivity.bff.models.RecommendationsResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.lang.NonNull;

import java.io.IOException;
import java.util.*;
import java.util.function.Predicate;

public class CombinedRecommendationsDataProvider implements RecommendationsDataProvider {

    private static final Predicate<RecommendationsResponse> ERROR_PREDICATE = res -> res.getError() != null;
    private final List<RecommendationsDataProvider> downstreamDataProviders;

    public CombinedRecommendationsDataProvider(List<RecommendationsDataProvider> downstreamDataProviders) {
        this.downstreamDataProviders = downstreamDataProviders;
    }

    @Override
    @NonNull
    public RecommendationsResponse provideRecommendations(@NotNull RecommendationRequest request) throws IOException {
        Set<Recommendation> recommendations = new HashSet<>();
        for (RecommendationsDataProvider provider : downstreamDataProviders) {
            RecommendationsResponse response = provider.provideRecommendations(request);
            if (response.getError() != null) {
                return response;
            }
            recommendations.addAll(new ArrayList<>(Arrays.asList(response.getRecommendations())));
        }
        return new RecommendationsResponse(
                recommendations
                        .stream()
                        .sorted()
                        .filter(recommendation -> recommendation.getPriority() >= 500)
                        .toArray((Recommendation[]::new)), null, 0);
    }
}
