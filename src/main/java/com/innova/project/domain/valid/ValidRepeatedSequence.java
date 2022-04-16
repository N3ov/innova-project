package com.innova.project.domain.valid;

import com.innova.project.infrastructure.exception.PasswordValidateException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import java.util.Set;
import java.util.stream.Collectors;

import static com.innova.project.infrastructure.exception.PasswordValidationErrorCode.PASSWORD_HAS_REPEATED_SEQUENCE;

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
                    "The password must can't contains the same sequence."
            );
        }
    }
}
