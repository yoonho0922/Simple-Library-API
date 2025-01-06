package com.project.simplelibraryapi.common.exception;

import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {

    private String errorMessage;
    private ApiErrorType errorType;

    public ApiException(String errorMessage, ApiErrorType errorType) {
        super(errorMessage);
        this.errorMessage = errorMessage;
        this.errorType = errorType;
    }

    public ApiException(ApiErrorType errorType) {
        this.errorMessage = errorType.getDescription();
        this.errorType = errorType;
    }
}
