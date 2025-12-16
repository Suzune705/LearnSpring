package com.learnspring.exception;

import java.net.UnknownServiceException;

public enum ErrorCode {

    USER_EXISTED(1001, "User is existed !!", HttpStatus.BAD_REQUEST),
    USERNAME_INVALID(1003, "Username must be at least 3 characters !! ", HttpStatus.BAD_REQUEST),
    USER_PASSWORD_INVALID(1004, "User password must be at least 3 characters !!", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1005, "User is not existed !! ", HttpStatus.NOT_FOUND),
    INVALID_TOKEN(1006,"The token has been expired !!", HttpStatus.BAD_REQUEST),
    INVALID_SIGNATURE(1007, "The signature is invalid  ", HttpStatus.BAD_REQUEST),
    UNAUTHORIZED(1008, "You don't have permission !!", HttpStatus.FORBIDDEN),
    UNAUTHENTICATED(1009, "Unauthenticated !!", HttpStatus.FORBIDDEN);



    private int code;
    private String message ;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
