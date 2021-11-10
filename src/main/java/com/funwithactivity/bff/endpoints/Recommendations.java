package com.funwithactivity.bff.endpoints;

import com.funwithactivity.bff.dataprovider.RecommendationsDataProvider;
import com.funwithactivity.bff.models.RecommendationRequest;
import com.funwithactivity.bff.models.RecommendationsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class Recommendations {

    @Autowired
    RecommendationsDataProvider dataProvider;


    @GetMapping("/recommendations")
    @ResponseBody
    public RecommendationsResponse getRecommendations(
            @RequestParam(name = "measurement_system", defaultValue = "metric") String measurementSystem,
            @RequestParam(name = "body_weight") String bodyWeight,
            @RequestParam(name = "body_height") String bodyHeight
    ) {
        return dataProvider.provideRecommendations(new RecommendationRequest(measurementSystem, bodyWeight, bodyHeight));
    }
}
