package com.innova.project.application.service;

import com.innova.project.application.dto.input.PasswordAskDTO;
import com.innova.project.application.dto.output.PasswordReplyDTO;
import com.innova.project.domain.service.ValidPasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PasswordService {

    private final ValidPasswordService validPasswordService;

    public PasswordReplyDTO isPass(PasswordAskDTO dto) {

        Boolean isPass = validContainSequence(dto.getPassword()) &&
                validRegex(dto.getPassword()) &&
                ValidEnoughLength(dto.getPassword());

        return new PasswordReplyDTO(
                isPass
        );

    }

    private Boolean ValidEnoughLength(String password) {
        return validPasswordService.isEnoughLength(password);
    }

    private Boolean validRegex(String password) {
        return validPasswordService.isRegex(password);
    }

    private Boolean validContainSequence(String password) {
        return validPasswordService.isContainSequence(password);
    }

}
