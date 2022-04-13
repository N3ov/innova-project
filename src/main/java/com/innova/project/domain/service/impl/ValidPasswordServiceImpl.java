package com.innova.project.domain.service.impl;

import com.innova.project.domain.service.ValidPasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Validated
public class ValidPasswordServiceImpl implements ValidPasswordService {

    private static final String DIGITS_AND_LOWER_CASE_REG = "^(?=.*[a-z])(?=.*?\\d).{5,12}";
    @Override
    public Boolean isRegex(@NotBlank String password) {
        Pattern pattern = Pattern.compile(DIGITS_AND_LOWER_CASE_REG);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    @Override
    public Boolean isEnoughLength(@NotBlank String password) {
        return password.length() > 4 && password.length() < 13;
    }

    @Override
    public Boolean isContainSequence(@NotBlank String password) {
        Set<Character> characterSet = password.chars().
                mapToObj(i -> (char) i).
                collect(Collectors.toSet());
        return characterSet.size() == password.length();
    }

    @Override
    public Boolean isNumericOrLowerLetters(@NotBlank String password) {

        char[] pd = password.toCharArray();
        for (char c : pd) {
             if(!Character.isLowerCase(c) && !Character.isDigit(c)) return false;
        }
        return true;
    }
}
