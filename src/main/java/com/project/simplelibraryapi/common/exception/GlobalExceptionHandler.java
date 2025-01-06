package com.project.simplelibraryapi.common.exception;

import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorResponse> handleApiException(ApiException e) {
        log.error("Api Exception occurred. message={}, className={}", e.getErrorMessage(), e.getClass().getName());
        return ResponseEntity.status(e.getErrorType().getHttpStatus())
            .body(new ErrorResponse(e.getErrorMessage(), e.getErrorType()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        log.error("Exception occurred. message={}, className={}", e.getMessage(), e.getClass().getName());
        ApiErrorType errorType = ApiErrorType.UNKNOWN;
        return ResponseEntity.status(errorType.getHttpStatus())
            .body(new ErrorResponse(errorType.getDescription(), errorType));
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoResourceFoundException(NoResourceFoundException e) {
        log.error("NoResourceFound Exception occurred. message={}, className={}", e.getMessage(), e.getClass().getName());
        ApiErrorType errorType = ApiErrorType.NOT_FOUND;
        return ResponseEntity.status(errorType.getHttpStatus())
            .body(new ErrorResponse(errorType.getDescription(), errorType));
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorResponse> handleBindException(BindException e) {
        log.warn("Bind Exception occurred. message={}, className={}", e.getMessage(), e.getClass().getName());
        ApiErrorType errorType = ApiErrorType.BAD_REQUEST;
        return ResponseEntity.status(errorType.getHttpStatus())
            .body(new ErrorResponse(createMessage(e), errorType));
    }

    private String createMessage(BindException e) {
        // 메시지 예시 : size-널이어서는 안됩니다. searchWord-공백일 수 없습니다. page-널이어서는 안됩니다.
        return e.getFieldErrors().stream()
            .map(fieldError -> fieldError.getField() + "-" + fieldError.getDefaultMessage())
            .collect(Collectors.joining(". ", "", "."));
    }
}
