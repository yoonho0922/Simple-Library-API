package com.project.simplelibraryapi.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiException extends RuntimeException {

    private String errorMessage;
    private ApiErrorType errorType;
}
