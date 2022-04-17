package com.innova.project.domain.valid;

import com.innova.project.infrastructure.exception.PasswordValidateException;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

import static com.innova.project.infrastructure.exception.PasswordValidationErrorCode.PASSWORD_LENGTH_DOES_NOT_MATCH;
import static com.innova.project.infrastructure.exception.PasswordValidationExceptionMessage.PASSWORD_LENGTH_DOES_NOT_MATCH_EXCEPTION;

@Component
@Validated
public class ValidPasswordLength implements PasswordValidation {
    @Override
    public void verify(@NotBlank String password) {

        if (password.length() < 5 || password.length() > 12) {
            throw new PasswordValidateException(
                    PASSWORD_LENGTH_DOES_NOT_MATCH,
                    PASSWORD_LENGTH_DOES_NOT_MATCH_EXCEPTION
            );
        }

    }
}
