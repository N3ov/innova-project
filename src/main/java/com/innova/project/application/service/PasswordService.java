package com.innova.project.application.service;

import com.innova.project.application.dto.input.PasswordAskDTO;
import com.innova.project.application.dto.output.PasswordReplyDTO;
import com.innova.project.domain.service.impl.ValidPasswordServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
@RequiredArgsConstructor
public class PasswordService {

    private final ValidPasswordServiceImpl validPasswordServiceImpl;

    public PasswordReplyDTO isPass(PasswordAskDTO dto) {

        //TODO decode password
        String decode = decodePassword(dto.getPassword(), dto.getKey());

        Assert.isNull(decode, "innova.password.valid.fail");

        Boolean isPass = validContainSequence(dto.getPassword()) &&
                validDigitsOrLowerCaseLetter(dto.getPassword()) &&
                ValidEnoughLength(dto.getPassword());

        return new PasswordReplyDTO(
                isPass,
                dto.getEncrypt(),
                dto.getPassword()
        );

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
