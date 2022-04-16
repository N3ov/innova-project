package com.innova.project.domain.valid;

import com.innova.project.infrastructure.exception.PasswordValidateException;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import java.util.Set;
import java.util.stream.Collectors;

@Validated
@Service
public class ValidRepeatedSequence implements PasswordValidation {

    @Override
    public void verify(@NotBlank String password) {
        Set<Character> characterSet = password.chars().
                mapToObj(i -> (char) i).
                collect(Collectors.toSet());
        if (characterSet.size() != password.length()) {
            throw new PasswordValidateException(1, "The password must can't contains the same sequence.");
        }
    }
}
