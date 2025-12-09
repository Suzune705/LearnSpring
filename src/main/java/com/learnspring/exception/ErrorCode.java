package com.learnspring.exception;

import java.net.UnknownServiceException;

public enum ErrorCode {

    USER_EXISTED(1001, "User is existed !!"),
    USERNAME_INVALID(1003, "Username must be at least 3 characters !! "),
    USER_PASSWORD_INVALID(1004, "User password must be at least 3 characters !!"),
    USER_NOT_EXISTED(1005, "User is not existed !! ");
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
