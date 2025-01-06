package com.project.simplelibraryapi.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.RequestInterceptor;
import java.nio.charset.StandardCharsets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

@Slf4j
public class NaverClientConfig {

    @Bean
    public RequestInterceptor requestInterceptor(
        @Value("${external.naver.headers.client-id}") String clientId,
        @Value("${external.naver.headers.client-secret}") String clientSecret
    ) {
        return requestTemplate -> {
            requestTemplate
                .header("X-Naver-Client-Id", clientId)
                .header("X-Naver-Client-Secret", clientSecret);

            log.info("Naver Client Request - URL: {}, Method: {}, Headers: {}, Query Params: {}, Body: {}",
                requestTemplate.url(),
                requestTemplate.method(),
                requestTemplate.headers(),
                requestTemplate.queries() != null ? requestTemplate.queries().toString() : "null",
                requestTemplate.body() != null ? new String(requestTemplate.body(), StandardCharsets.UTF_8) : "null");
        };
    }

    @Bean
    public NaverErrorDecoder naverErrorDecoder(ObjectMapper objectMapper) {
        return new NaverErrorDecoder(objectMapper);
    }
}
