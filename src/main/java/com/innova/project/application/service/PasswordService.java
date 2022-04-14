package com.innova.project.application.service;

import com.innova.project.application.dto.input.PasswordAskDTO;
import com.innova.project.application.dto.output.PasswordReplyDTO;
import com.innova.project.domain.service.ValidPasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@Validated
@RequiredArgsConstructor
public class PasswordService {

    private final ValidPasswordService validPasswordService;

    @Validated
    public PasswordReplyDTO isPass(@Valid PasswordAskDTO dto) {

        Boolean isPass = validContainSequence(dto.getPassword()) &&
                validRegex(dto.getPassword()) &&
                validEnoughLength(dto.getPassword()) &&
                validNumericOrLowerLetters(dto.getPassword());

        return new PasswordReplyDTO(
                isPass
        );
    }

    private Boolean validNumericOrLowerLetters(String password) {
        return validPasswordService.isNumericOrLowerLetters(password);
    }

    private Boolean validEnoughLength(String password) {
        return validPasswordService.isEnoughLength(password);
    }

    private Boolean validRegex(String password) {
        return validPasswordService.isRegex(password);
    }

    private Boolean validContainSequence(String password) {
        return validPasswordService.isNotRepeatedSequence(password);
    }

}
