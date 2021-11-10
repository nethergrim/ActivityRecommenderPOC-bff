package com.funwithactivity.bff.dataprovider;

import com.funwithactivity.bff.models.Recommendation;
import com.funwithactivity.bff.models.RecommendationRequest;
import com.funwithactivity.bff.models.RecommendationsResponse;
import org.springframework.lang.NonNull;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class CombinedRecommendationsDataProvider implements RecommendationsDataProvider {

    private static final Predicate<RecommendationsResponse> ERROR_PREDICATE = res -> res.getError() != null;
    private final List<RecommendationsDataProvider> downstreamDataProviders;

    public CombinedRecommendationsDataProvider(List<RecommendationsDataProvider> downstreamDataProviders) {
        this.downstreamDataProviders = downstreamDataProviders;
    }

    @Override
    @NonNull
    public RecommendationsResponse provideRecommendations(RecommendationRequest request) {
        Optional<String> error = downstreamDataProviders.stream().map(provider -> provider.provideRecommendations(request))
                .filter(ERROR_PREDICATE).map(RecommendationsResponse::getError).findFirst();
        if (error.isPresent()) {
            return new RecommendationsResponse(new Recommendation[0], error.get());
        }
        return new RecommendationsResponse(
                downstreamDataProviders.stream().map(provider -> provider.provideRecommendations(request))
                        .flatMap((Function<RecommendationsResponse, Stream<Recommendation>>) res -> Arrays.stream(res.getRecommendations()))
                        .sorted()
                        .distinct()
                        .toArray(Recommendation[]::new)
                , null);
    }
}
