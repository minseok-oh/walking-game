package org.calorieburn.server.global.exception.type;

import lombok.Getter;
import lombok.ToString;
import org.calorieburn.server.global.exception.ErrorCode;

@Getter
@ToString
public class ApiException extends RuntimeException {

    private final ErrorCode errorCode;
    private final String errorDescription;

    public ApiException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.errorDescription = errorCode.getMessage();
    }

    public ApiException(ErrorCode errorCode, String errorDescription) {
        super(errorDescription);
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
    }

    public ApiException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.getMessage(), cause);
        this.errorCode = errorCode;
        this.errorDescription = errorCode.getMessage();
    }

    public ApiException(ErrorCode errorCode, String errorDescription, Throwable cause) {
        super(errorDescription, cause);
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
    }
}