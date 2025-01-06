package com.project.simplelibraryapi.common.exception;

public record ErrorResponse(
    String errorMessage,
    ApiErrorType errorType
) {

}