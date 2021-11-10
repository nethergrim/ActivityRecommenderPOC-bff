package com.funwithactivity.bff.dataprovider.external;

import com.funwithactivity.bff.models.RecommendationRequest;

public interface RequestMapper {
    Object map(RecommendationRequest req);
}
