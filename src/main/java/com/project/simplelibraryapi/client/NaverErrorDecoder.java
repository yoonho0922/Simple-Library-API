package com.project.simplelibraryapi.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.simplelibraryapi.client.error.NaverErrorResponse;
import com.project.simplelibraryapi.common.exception.ApiErrorType;
import com.project.simplelibraryapi.common.exception.ApiException;
import feign.Response;
import feign.codec.ErrorDecoder;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NaverErrorDecoder implements ErrorDecoder {

    private final ObjectMapper objectMapper;

    public NaverErrorDecoder(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Exception decode(String methodKey, Response response) {
        try {
            String body = new String(response.body().asInputStream().readAllBytes(), StandardCharsets.UTF_8);
            NaverErrorResponse errorResponse = objectMapper.readValue(body, NaverErrorResponse.class);
            throw new ApiException(errorResponse.getErrorMessage(), ApiErrorType.EXTERNAL_API_ERROR);
        } catch (IOException e) {
            log.error("에러 메세지 파싱 에러 code={}, request={}, methodKey={}, errorMessage={}",
                response.status(), response.request(), methodKey, e.getMessage());
            throw new ApiException("네이버 메세지 파싱 오류", ApiErrorType.UNKNOWN);
        }
    }
}