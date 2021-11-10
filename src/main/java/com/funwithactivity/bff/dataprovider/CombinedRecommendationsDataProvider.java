package com.funwithactivity.bff.dataprovider;

import com.funwithactivity.bff.models.Recommendation;
import com.funwithactivity.bff.models.RecommendationRequest;
import com.funwithactivity.bff.models.RecommendationsResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.lang.NonNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.IntFunction;
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
        List<Recommendation> recommendations = new ArrayList<>();
        for (RecommendationsDataProvider provider : downstreamDataProviders) {
            RecommendationsResponse response = provider.provideRecommendations(request);
            if (response.getError() != null) {
                return new RecommendationsResponse(null, response.getError());
            }
            recommendations.addAll(new ArrayList<>(Arrays.asList(response.getRecommendations())));
        }
        return new RecommendationsResponse(recommendations.stream().sorted().distinct().toArray((Recommendation[]::new)), null);
    }
}
