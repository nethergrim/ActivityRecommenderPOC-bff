package com.funwithactivity.bff.dataprovider;

import com.funwithactivity.bff.models.Recommendation;
import com.funwithactivity.bff.models.RecommendationRequest;
import com.funwithactivity.bff.models.RecommendationsResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.lang.NonNull;

import java.io.IOException;
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
    public RecommendationsResponse provideRecommendations(@NotNull RecommendationRequest request) throws IOException {
        Optional<String> error = Optional.empty();
        for (RecommendationsDataProvider downstreamDataProvider : downstreamDataProviders) {
            RecommendationsResponse recommendationsResponse = downstreamDataProvider.provideRecommendations(request);
            if (ERROR_PREDICATE.test(recommendationsResponse)) {
                String recommendationsResponseError = recommendationsResponse.getError();
                error = Optional.of(recommendationsResponseError);
                break;
            }
        }
        if (error.isPresent()) {
            return new RecommendationsResponse(new Recommendation[0], error.get());
        }
        return new RecommendationsResponse(
                downstreamDataProviders.stream().map(provider -> {
                            try {
                                return provider.provideRecommendations(request);
                            } catch (IOException e) {
                                throw new NullPointerException(e.getLocalizedMessage()); // TODO fix this shit
                            }
                        })
                        .flatMap((Function<RecommendationsResponse, Stream<Recommendation>>) res -> Arrays.stream(res.getRecommendations()))
                        .sorted()
                        .distinct()
                        .toArray(Recommendation[]::new)
                , null);
    }
}
