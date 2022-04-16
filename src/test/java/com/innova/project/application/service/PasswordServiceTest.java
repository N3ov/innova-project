package com.innova.project.application.service;

import com.innova.project.application.dto.input.PasswordAskDTO;
import com.innova.project.application.dto.output.PasswordReplyDTO;
import com.innova.project.domain.service.impl.ValidPasswordServiceImpl;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class PasswordServiceTest {

    @InjectMocks
    PasswordService passwordService;

    @Spy
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

    @Nested
    class whe_valid_password {
        @Test
        void if_password_has_special_character_expect_invalid() {
            String password = "a1!23wish";
            PasswordAskDTO dto = setup(password);
            PasswordReplyDTO replyDTO = new PasswordReplyDTO();

            PasswordReplyDTO result = passwordService.verifyPassword(dto);
            assertEquals(replyDTO.getIsPass(), result.getIsPass());
            BDDAssertions.then(passwordService.verifyPassword(dto)).isNotNull();
        }

        @Test
        void if_password_has_uppercase_letters_expect_invalid() {
            String password = "a1A23wish";
            PasswordAskDTO dto = setup(password);
            PasswordReplyDTO replyDTO = new PasswordReplyDTO();

            PasswordReplyDTO result = passwordService.verifyPassword(dto);

            assertEquals(replyDTO.getIsPass(), result.getIsPass());
            BDDAssertions.then(passwordService.verifyPassword(dto)).isNotNull();
        }

        @Test
        void if_password_has_all_numeric_expect_invalid() {
            String password = "1234576";
            PasswordAskDTO dto = setup(password);
            PasswordReplyDTO replyDTO = new PasswordReplyDTO();

            PasswordReplyDTO result = passwordService.verifyPassword(dto);

            assertEquals(replyDTO.getIsPass(), result.getIsPass());
            BDDAssertions.then(passwordService.verifyPassword(dto)).isNotNull();
        }

        @Test
        void if_password_has_all_letters_expect_invalid() {
            String password = "abkidjw";
            PasswordAskDTO dto = setup(password);
            PasswordReplyDTO replyDTO = new PasswordReplyDTO();

            PasswordReplyDTO result = passwordService.verifyPassword(dto);

            assertEquals(replyDTO.getIsPass(), result.getIsPass());
            BDDAssertions.then(passwordService.verifyPassword(dto)).isNotNull();
        }

        @Test
        void if_password_is_accept_except_valid() {

            String password = "a123wish";
            PasswordAskDTO dto = setup(password);
            PasswordReplyDTO replyDTO = new PasswordReplyDTO();

            PasswordReplyDTO result = passwordService.verifyPassword(dto);

            assertEquals(replyDTO.getIsPass(), result.getIsPass());
            BDDAssertions.then(passwordService.verifyPassword(dto)).isNotNull();

        }
    }


}