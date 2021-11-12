package com.funwithactivity.bff;

import com.funwithactivity.bff.dataprovider.CombinedRecommendationsDataProvider;
import com.funwithactivity.bff.dataprovider.RecommendationsDataProvider;
import com.funwithactivity.bff.dataprovider.external.service1.ExtService1DataProvider;
import com.funwithactivity.bff.dataprovider.external.service2.ExtService2DataProvider;
import com.funwithactivity.bff.dataprovider.external.service3.Service3DataProvider;
import okhttp3.OkHttpClient;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("unused")
@Configuration
public class AppConfiguration {

    @Bean
    public RecommendationsDataProvider recommendationsDataProvider(OkHttpClient okHttpClient) {
        return new CombinedRecommendationsDataProvider(Arrays.asList(
                new ExtService1DataProvider(okHttpClient),
                new ExtService2DataProvider(okHttpClient),
                new Service3DataProvider(okHttpClient)
        ));
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .callTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .build();
    }
}
