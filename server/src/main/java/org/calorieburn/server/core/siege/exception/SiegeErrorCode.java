package org.calorieburn.server.core.siege.exception;

import org.calorieburn.server.global.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public enum SiegeErrorCode implements ErrorCode {
    S001("S001", HttpStatus.BAD_REQUEST, "유효하지 않은 제목입니다."),
    S002("S002", HttpStatus.BAD_REQUEST, "유효하지 않은 학교1입니다."),
    S003("S003", HttpStatus.BAD_REQUEST, "유효하지 않은 학교2입니다."),
    S004("S004", HttpStatus.BAD_REQUEST, "유효하지 않은 시작 시간입니다."),
    S005("S005", HttpStatus.BAD_REQUEST, "유효하지 않은 종료 시간입니다.")
    ;

    private final String code;
    private final HttpStatus httpStatus;
    private final String message;

    SiegeErrorCode(String code, HttpStatus httpStatus, String message) {
        this.code = code;
        this.httpStatus = httpStatus;
        this.message = message;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
