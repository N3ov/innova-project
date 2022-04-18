package com.innova.project.application.service;

import com.innova.project.application.dto.input.PasswordAskDTO;
import com.innova.project.application.dto.output.PasswordReplyDTO;
import com.innova.project.domain.valid.PasswordValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.Set;

@Service
@Validated
@RequiredArgsConstructor
public class PasswordService {
    private Set<PasswordValidation> validations;

    @Autowired
    public PasswordService(Set<PasswordValidation> validations) {
        this.validations = validations;
    }

    @Validated
    public PasswordReplyDTO verifyPassword(@Valid PasswordAskDTO dto) {

        for (PasswordValidation validation : validations) {
            validation.verify(dto.getPassword());
        }

        return new PasswordReplyDTO(
                System.currentTimeMillis(),
                true
        );
    }

}
