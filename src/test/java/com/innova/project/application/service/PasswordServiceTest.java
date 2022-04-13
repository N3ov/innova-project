package com.innova.project.application.service;

import com.innova.project.application.dto.input.PasswordAskDTO;
import com.innova.project.application.dto.output.PasswordReplyDTO;
import com.innova.project.domain.service.impl.ValidPasswordServiceImpl;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class PasswordServiceTest {

    @InjectMocks
    PasswordService passwordService;

    @Mock
    private ValidPasswordServiceImpl validPasswordService;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    PasswordAskDTO setup(String password) {
        PasswordAskDTO dto = new PasswordAskDTO();
        dto.setPassword(password);
        return dto;
    }

    @Test
    void if_password_can_be_passed() {

//        String password = "a123wish";
//        PasswordAskDTO dto = setup(password);
//        given(validPasswordService.isNumericOrLowerLetters(anyString())).willReturn(true);
//        given(validPasswordService.isEnoughLength(anyString())).willReturn(true);
//        given(validPasswordService.isNotRepeatedSequence(anyString())).willReturn(true);
//        given(validPasswordService.isNotRepeatedSequence(anyString())).willReturn(true);
//        PasswordReplyDTO replyDTO = new PasswordReplyDTO(true);
//        BDDAssertions.then(passwordService.isPass(dto)).isNotNull();
//        then(validPasswordService).should().isNotRepeatedSequence(password);

    }
}