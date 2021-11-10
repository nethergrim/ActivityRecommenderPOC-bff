package com.funwithactivity.bff;

import com.funwithactivity.bff.dataprovider.CombinedRecommendationsDataProvider;
import com.funwithactivity.bff.dataprovider.RecommendationsDataProvider;
import com.funwithactivity.bff.dataprovider.external.service1.ExtService1DataProvider;
import com.funwithactivity.bff.dataprovider.external.service2.ExtService2DataProvider;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Configuration
public class AppConfiguration {

    @Bean
    public RecommendationsDataProvider recommendationsDataProvider() {
        return new CombinedRecommendationsDataProvider(Arrays.asList(
                new ExtService1DataProvider(),
                new ExtService2DataProvider()
        ));
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}
