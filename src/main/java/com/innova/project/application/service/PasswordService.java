package com.innova.project.application.service;

import com.innova.project.domain.service.impl.ValidPasswordServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PasswordService {

    private final ValidPasswordServiceImpl validPasswordServiceImpl;

    public Boolean isPass(String password, String key){

        //TODO decode password

        String decode = decodePassword(password, key);
        if (null == decode) return false;

        //TODO response interface
        return validContainSequence(password) &&
                validDigitsOrLowerCaseLetter(password) &&
                ValidEnoughLength(password);

    }

    private String decodePassword(String encode, String key) {
        return null;
    }

    private Boolean ValidEnoughLength(String password) {
        return validPasswordServiceImpl.isEnoughLength(password);
    }

    private Boolean validDigitsOrLowerCaseLetter(String password) {
        return validPasswordServiceImpl.isDigitsOrLowerCaseLetter(password);
    }

    private Boolean validContainSequence(String password) {
        return validPasswordServiceImpl.isContainSequence(password);
    }

}
