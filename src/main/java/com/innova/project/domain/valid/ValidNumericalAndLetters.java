package com.innova.project.domain.valid;

import com.innova.project.infrastructure.exception.PasswordValidateException;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Validated
@Service
public class ValidNumericalAndLetters implements PasswordValidation {

    private static final String DIGITS_AND_LOWER_CASE_REG = "^(?=.*[a-z]+)(?=.*\\d+)[a-z\\d]+$";
    @Override
    public void verify(@NotBlank String password) {

        Pattern pattern = Pattern.compile(DIGITS_AND_LOWER_CASE_REG);
        Matcher matcher = pattern.matcher(password);
        System.out.println("valid numeric digits and lowercase letters: " + password);

        if (!matcher.matches()){
          throw new PasswordValidateException(0, "the password must consist of numerical digits and lowercase letters");
        }
    }
}
