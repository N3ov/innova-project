package com.innova.project.infrastructure.exception;

import lombok.Getter;

public class ValidPasswordException extends RuntimeException {

    @Getter
    private final Integer status;

    public ValidPasswordException(Integer status, String message){
        super(message);
        this.status = status;
    }

    public ValidPasswordException(Integer status, String message, Throwable cause) {
        super(message, cause);
        this.status = status;
    }

}
