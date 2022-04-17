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

import static com.innova.project.domain.valid.PasswordTestFixture.*;
import static com.innova.project.infrastructure.exception.PasswordValidationExceptionMessage.*;
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
        return new PasswordAskDTO(password);
    }

    @Nested
    class when_valid_password_passed {
        private final long currTime = random.nextLong();

        @Test
        void if_password_has_correct_character_expect_pass() {
            String password = createCorrectPassword(5);
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

    }

    @Nested
    class when_valid_password {
        @Test
        void if_password_has_special_character_expect_invalid() {
            String password = createCorrectPassword(5)+"!";
            PasswordAskDTO dto = setup(password);

            doThrow(PasswordValidateException.class).when(passwordValidation)
                    .verify(dto.getPassword());
            assertThrows(
                    PasswordValidateException.class,
                    () -> unit.verifyPassword(dto),
                    PASSWORD_NUMERICAL_DIGITS_AND_LOWERCASE_LETTERS_EXCEPTION
            );
        }

        @Test
        void if_password_has_uppercase_letters_expect_invalid() {
            String password = createUpperCaseLettersPassword(5);
            PasswordAskDTO dto = setup(password);

            doThrow(PasswordValidateException.class).when(passwordValidation).verify(dto.getPassword());
            assertThrows(
                    PasswordValidateException.class,
                    () -> unit.verifyPassword(dto),
                    PASSWORD_NUMERICAL_DIGITS_AND_LOWERCASE_LETTERS_EXCEPTION
            );
        }

        @Test
        void if_password_has_all_numeric_expect_invalid() {
            String password = createNumericalPassword(5);
            PasswordAskDTO dto = setup(password);
            doThrow(PasswordValidateException.class).when(passwordValidation)
                    .verify(dto.getPassword());
            assertThrows(
                    PasswordValidateException.class,
                    () -> unit.verifyPassword(dto),
                    PASSWORD_NUMERICAL_DIGITS_AND_LOWERCASE_LETTERS_EXCEPTION
            );
        }

        @Test
        void if_password_has_all_letters_expect_invalid() {
            String password = createLowerLettersPassword(7);
            PasswordAskDTO dto = setup(password);
            doThrow(PasswordValidateException.class).when(passwordValidation).verify(dto.getPassword());
            assertThrows(
                    PasswordValidateException.class,
                    () -> unit.verifyPassword(dto),
                    PASSWORD_NUMERICAL_DIGITS_AND_LOWERCASE_LETTERS_EXCEPTION
            );
        }
    }

    @Nested
    class valid_password_length {
        @Test
        void if_password_length_not_enough_and_expect_not_pass() {
            String password = createCorrectPassword(3);
            PasswordAskDTO dto = setup(password);
            doThrow(PasswordValidateException.class).when(passwordValidation).verify(dto.getPassword());
            assertThrows(
                    PasswordValidateException.class,
                    () -> unit.verifyPassword(dto),
                    PASSWORD_LENGTH_DOES_NOT_MATCH_EXCEPTION
            );
        }

        @Test
        void if_password_length_over_12_and_expect_not_pass() {
            String password = createCorrectPassword(13);
            PasswordAskDTO dto = setup(password);
            doThrow(PasswordValidateException.class).when(passwordValidation).verify(dto.getPassword());
            assertThrows(
                    PasswordValidateException.class,
                    () -> unit.verifyPassword(dto),
                    PASSWORD_LENGTH_DOES_NOT_MATCH_EXCEPTION
            );
        }
    }

    @Nested
    class valid_sequence {
        @Test
        void if_password_has_same_squence() {
            String password = createCorrectPassword(4);
            PasswordAskDTO dto = setup(password+password);
            doThrow(PasswordValidateException.class).when(passwordValidation).verify(dto.getPassword());
            assertThrows(
                    PasswordValidateException.class,
                    () -> unit.verifyPassword(dto),
                    PASSWORD_REPEATED_SEQUENCE_EXCEPTION
            );
        }
    }


    private PasswordReplyDTO setUpReplyDTO(long time, boolean verify) {
        PasswordReplyDTO replyDTO = new PasswordReplyDTO();
        replyDTO.setValidTime(time);
        replyDTO.setVerified(verify);
        return replyDTO;
    }


}