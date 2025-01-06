package com.project.simplelibraryapi.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ApiErrorType {
    UNKNOWN("알 수 없는 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    BAD_REQUEST("잘못된 요청입니다.", HttpStatus.BAD_REQUEST),
    EXTERNAL_API_ERROR("외부 API 요청 중 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String description;
    private final HttpStatus httpStatus;
}
