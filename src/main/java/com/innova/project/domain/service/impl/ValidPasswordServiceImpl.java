package com.innova.project.domain.service.impl;

import com.innova.project.domain.service.ValidPasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Validated
public class ValidPasswordServiceImpl implements ValidPasswordService {

    @Override
    public Boolean isDigitsOrLowerCaseLetter(@NotBlank String password) {

        char[] charList = password.toCharArray();

        for (char c : charList) {
            return !Character.isDigit(c) || (!Character.isLowerCase(c) && !Character.isAlphabetic(c));
        }

        return true;
    }

    @Override
    public Boolean isEnoughLength(@NotBlank String password) {
        return password.length() < 4 || password.length() > 13;
    }

    @Override
    public Boolean isContainSequence(@NotBlank String password) {
        Set<Character> characterSet = password.chars().
                mapToObj(i -> (char) i).
                collect(Collectors.toSet());
        return characterSet.size() == password.length();
    }
}
