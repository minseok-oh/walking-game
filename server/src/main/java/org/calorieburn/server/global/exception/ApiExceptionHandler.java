package org.calorieburn.server.global.exception;

import lombok.extern.slf4j.Slf4j;
import org.calorieburn.server.global.api.ApiErrorResponse;
import org.calorieburn.server.global.exception.type.ApiException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "com.wootecam.festivals.domain")
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ApiExceptionHandler {

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<ApiErrorResponse> handleIllegalArgumentException(IllegalArgumentException exception) {
        ApiErrorResponse errorResponse = ApiErrorResponse.of(GlobalErrorCode.INTERNAL_SERVER_ERROR.getCode(),
                exception.getMessage());

        log.error("{}", errorResponse);

        return ResponseEntity
                .status(GlobalErrorCode.INTERNAL_SERVER_ERROR.getHttpStatus())
                .body(errorResponse);
    }

    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity<ApiErrorResponse> handleApiException(ApiException exception) {
        ApiErrorResponse errorResponse = ApiErrorResponse.of(exception.getErrorCode().getCode(),
                exception.getErrorDescription());

        log.error("{}", errorResponse);

        return ResponseEntity
                .status(exception.getErrorCode().getHttpStatus())
                .body(errorResponse);
    }
}