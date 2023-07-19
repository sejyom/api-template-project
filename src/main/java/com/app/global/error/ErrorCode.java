package com.app.global.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ErrorCode {

    ErrorCode(HttpStatus httpStatus, String errorCode, String message) {
        this.httpStatus = httpStatus;
        this.ErrorCode = errorCode;
        this.message = message;
    }

    private HttpStatus httpStatus;
    private String ErrorCode;
    private String message;
}
