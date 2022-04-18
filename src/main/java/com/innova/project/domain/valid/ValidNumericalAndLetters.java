package com.innova.project.domain.valid;

import com.innova.project.infrastructure.exception.PasswordAsserts;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.innova.project.infrastructure.exception.PasswordValidationErrorCode.PASSWORD_MUST_CONSIST_OF_NUMERICAL_DIGITS_AND_LOWERCASE_LETTERS;
import static com.innova.project.infrastructure.exception.PasswordValidationExceptionMessage.PASSWORD_NUMERICAL_DIGITS_AND_LOWERCASE_LETTERS_EXCEPTION;

@Validated
@Component
public class ValidNumericalAndLetters implements PasswordValidation {

    private static final String DIGITS_AND_LOWER_CASE_REG = "^(?=.*[a-z]+)(?=.*\\d+)[a-z\\d]+$";

    @Override
    public void verify(@NotBlank String password) {

        Pattern pattern = Pattern.compile(DIGITS_AND_LOWER_CASE_REG);
        Matcher matcher = pattern.matcher(password);

        PasswordAsserts.isTrue(
                matcher.matches(),
                PASSWORD_MUST_CONSIST_OF_NUMERICAL_DIGITS_AND_LOWERCASE_LETTERS,
                PASSWORD_NUMERICAL_DIGITS_AND_LOWERCASE_LETTERS_EXCEPTION
        );
    }
}
