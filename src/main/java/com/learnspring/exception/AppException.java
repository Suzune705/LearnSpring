package com.learnspring.exception;

import com.learnspring.enums.ErrorCodeType;

public class AppException extends  RuntimeException{

    private ErrorCodeType errorCodeType;

    public AppException(ErrorCodeType errorCodeType) {
        super(errorCodeType.getMessage());
        this.errorCodeType = errorCodeType;
    }

    public ErrorCodeType getErrorCode(){
        return this.errorCodeType;
    }

    public void setErrorCode(ErrorCodeType errorCodeType) {
        this.errorCodeType = errorCodeType;
    }
}
