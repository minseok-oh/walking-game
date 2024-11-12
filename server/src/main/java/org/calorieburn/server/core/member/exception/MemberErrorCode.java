package org.calorieburn.server.core.member.exception;

import lombok.Getter;
import org.calorieburn.server.global.exception.ErrorCode;
import org.springframework.http.HttpStatus;

@Getter
public enum MemberErrorCode implements ErrorCode {
    M001(HttpStatus.BAD_REQUEST, "M001", "이름은 필수 입력 값입니다."),
    M002(HttpStatus.BAD_REQUEST, "M002", "이메일은 필수 입력 값입니다."),
    M003(HttpStatus.BAD_REQUEST, "M003", "비밀번호는 필수 입력 값입니다."),
    M004(HttpStatus.BAD_REQUEST, "M004", "전화번호는 필수 입력 값입니다."),
    M005(HttpStatus.BAD_REQUEST, "M005", "학교는 필수 입력 값입니다."),
    M006(HttpStatus.BAD_REQUEST, "M006", "비밀번호가 일치하지 않습니다."),
    M007(HttpStatus.UNAUTHORIZED, "M007", "액세스 토큰이 유효하지 않습니다."),
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    MemberErrorCode(HttpStatus httpStatus, String code, String message) {
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
