package com.innova.project.infrastructure.exception;

import lombok.Getter;

public class PasswordValidateException extends RuntimeException {

    private static final long serialVersionUID = 1231552452345L;
    @Getter
    private final Integer errorCode;

    public PasswordValidateException(Integer errorCode, String message){
        super(message);
        this.errorCode = errorCode;
    }

    public PasswordValidateException(Integer errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

}
