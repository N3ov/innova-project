package com.innova.project.application.service;

import com.innova.project.application.dto.input.PasswordAskDTO;
import com.innova.project.application.dto.output.PasswordReplyDTO;
import com.innova.project.domain.valid.PasswordValidation;
import com.innova.project.infrastructure.exception.PasswordValidateException;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@SpringJUnitConfig(ValidationAutoConfiguration.class)
@ExtendWith(MockitoExtension.class)
class PasswordServiceTest {

    @SpyBean
    private PasswordService unit;

    @MockBean
    private PasswordValidation passwordValidation;

    private final Random random = new Random();

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
    class when_valid_password {
        private final long currTime = random.nextLong();

        @Test
        void if_password_has_correct_character_expect_pass() {
            String password = "a123wish";
            PasswordAskDTO dto = setup(password);
            PasswordReplyDTO replyDTO = setUpReplyDTO(
                    currTime,
                    true
            );
            doNothing().when(passwordValidation).verify(anyString());
            PasswordReplyDTO result = unit.verifyPassword(dto);
            BDDAssertions.then(unit.verifyPassword(dto)).isNotNull();
            then(passwordValidation).should(times(2))
                    .verify(eq(dto.getPassword()));
            assertEquals(replyDTO.getVerified(), result.getVerified());
        }

        @Test
        void if_password_has_special_character_expect_invalid() {
            String password = "a1!23wish";
            PasswordAskDTO dto = setup(password);

            doThrow(PasswordValidateException.class).when(passwordValidation).verify(dto.getPassword());
            assertThrows(PasswordValidateException.class, () -> unit.verifyPassword(dto));
        }

        @Test
        void if_password_has_uppercase_letters_expect_invalid() {
            String password = "a1A23wish";
            PasswordAskDTO dto = setup(password);

            doThrow(PasswordValidateException.class).when(passwordValidation).verify(dto.getPassword());
            assertThrows(PasswordValidateException.class, () -> unit.verifyPassword(dto));
        }

        @Test
        void if_password_has_all_numeric_expect_invalid() {
            String password = "1234576";
            PasswordAskDTO dto = setup(password);
            doThrow(PasswordValidateException.class).when(passwordValidation).verify(dto.getPassword());
            assertThrows(PasswordValidateException.class, () -> unit.verifyPassword(dto));
        }

        @Test
        void if_password_has_all_letters_expect_invalid() {
            String password = "abkidjw";
            PasswordAskDTO dto = setup(password);
            doThrow(PasswordValidateException.class).when(passwordValidation).verify(dto.getPassword());
            assertThrows(PasswordValidateException.class, () -> unit.verifyPassword(dto));

        }

        @Test
        void if_password_is_accept_except_valid() {

            String password = "a123wish";
            PasswordAskDTO dto = setup(password);
            doThrow(PasswordValidateException.class).when(passwordValidation).verify(dto.getPassword());
            assertThrows(PasswordValidateException.class, () -> unit.verifyPassword(dto));
        }
    }

    private PasswordReplyDTO setUpReplyDTO(long time, boolean verify) {
        PasswordReplyDTO replyDTO = new PasswordReplyDTO();
        replyDTO.setValidTime(time);
        replyDTO.setVerified(verify);
        return replyDTO;
    }


}