package com.innova.project.domain.valid;

import com.innova.project.infrastructure.exception.PasswordValidateException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.innova.project.infrastructure.exception.PasswordValidationErrorCode.PASSWORD_MUST_CONSIST_OF_NUMERICAL_DIGITS_AND_LOWERCASE_LETTERS;

@Validated
@Component
public class ValidNumericalAndLetters implements PasswordValidation {

    private static final String DIGITS_AND_LOWER_CASE_REG = "^(?=.*[a-z]+)(?=.*\\d+)[a-z\\d]+$";

    @Override
    public void verify(@NotBlank String password) {

        Pattern pattern = Pattern.compile(DIGITS_AND_LOWER_CASE_REG);
        Matcher matcher = pattern.matcher(password);

        if (!matcher.matches()) {
            throw new PasswordValidateException(
                    PASSWORD_MUST_CONSIST_OF_NUMERICAL_DIGITS_AND_LOWERCASE_LETTERS,
                    "the password must consist of numerical digits and lowercase letters"
            );
        }
    }
}
