package org.calorieburn.server.core.health.exception;

import org.calorieburn.server.global.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public enum HealthInfoErrorCode implements ErrorCode {
    H001(HttpStatus.BAD_REQUEST, "H001", "걸음 수는 필수 입력 값입니다."),
    H002(HttpStatus.BAD_REQUEST, "H002", "칼로리 소모량은 필수 입력 값입니다."),
    H003(HttpStatus.BAD_REQUEST, "H003", "이전 체중은 필수 입력 값입니다."),
    H004(HttpStatus.BAD_REQUEST, "H004", "현재 체중은 필수 입력 값입니다."),
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    HealthInfoErrorCode(HttpStatus httpStatus, String code, String message) {
        this.code = code;
        this.httpStatus = httpStatus;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getCode() {
        return code;
    }
}
