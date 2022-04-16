package com.innova.project.domain.valid;

import com.innova.project.infrastructure.exception.PasswordValidateException;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Service
@Validated
public class ValidLength implements PasswordValidation {
    @Override
    public void verify(@NotBlank String password) {

        if (password.length() < 5 || password.length() > 12) {
            throw new PasswordValidateException(3, "the password length must between 5 and 12");
        }

    }
}
