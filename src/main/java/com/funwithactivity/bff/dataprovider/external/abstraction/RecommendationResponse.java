package com.funwithactivity.bff.dataprovider.external.abstraction;

import com.funwithactivity.bff.models.Recommendation;

public interface RecommendationResponse {
    Recommendation toRecommendation();
}
