package com.innova.project.infrastructure.exception;

import org.springframework.lang.Nullable;

public interface PasswordAsserts {

    static void notNull(@Nullable Object object, Integer code, String errorMessage) {
        if (object == null) {
            throw new PasswordValidateException(code, errorMessage);
        }
    }

    static void notEmpty(@Nullable String str, Integer code, String errorMessage) {
        if (str == null || str.length() == 0) {
            throw new PasswordValidateException(code, errorMessage);
        }
    }

    static void isTrue(boolean expression, Integer code, String errorMessage){
        if (!expression) {
            throw new PasswordValidateException(code, errorMessage);
        }
    }

    static void isFalse(boolean expression, Integer code, String errorMessage) {
        if (expression) {
            throw new PasswordValidateException(code, errorMessage);
        }
    }

}
