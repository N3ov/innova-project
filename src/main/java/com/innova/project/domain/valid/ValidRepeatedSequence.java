package com.innova.project.domain.valid;

import com.innova.project.infrastructure.exception.PasswordValidateException;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import java.util.Set;
import java.util.stream.Collectors;

import static com.innova.project.infrastructure.exception.PasswordValidationErrorCode.PASSWORD_HAS_REPEATED_SEQUENCE;
import static com.innova.project.infrastructure.exception.PasswordValidationExceptionMessage.PASSWORD_REPEATED_SEQUENCE_EXCEPTION;

@Validated
@Component
public class ValidRepeatedSequence implements PasswordValidation {

    @Override
    public void verify(@NotBlank String password) {

        Set<Character> characterSet = password.chars().
                mapToObj(i -> (char) i).
                collect(Collectors.toSet());

        if (characterSet.size() != password.length()) {
            throw new PasswordValidateException(
                    PASSWORD_HAS_REPEATED_SEQUENCE,
                    PASSWORD_REPEATED_SEQUENCE_EXCEPTION
            );
        }
    }
}
