package com.innova.project.domain.valid;

import com.innova.project.infrastructure.exception.PasswordValidateException;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class ValidPasswordLengthTest {
    private ValidPasswordLength validPasswordLength;

    @BeforeEach
    void setUp() {
        validPasswordLength = mock(ValidPasswordLength.class);
    }

    @Nested
    @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
    class when_valid_password_is_digits_or_lowercase_letters {
        @Test
        void if_password_are_digits() {
            String password = "12312444";
//            when(validPasswordLength.verify(password)).thenThrow(PasswordValidateException.class);
            validPasswordLength.verify(password);
            verify(validPasswordLength, times(1)).verify(password);
            PasswordValidateException thrown = assertThrows(
                    PasswordValidateException.class,
                    () -> validPasswordLength.verify(password)
            );
            assertEquals(
                    "the password must consist of numerical digits and lowercase letters",
                    thrown.getMessage());
        }

        @Test
        void if_password_are_lowercase_letter() {
            String password = "asdbbsdad";
            validPasswordLength.verify(password);
            verify(validPasswordLength, times(1)).verify(password);
        }

        @Test
        void if_password_has_lowercase_letters_and_digit() {
            String password = "a139b24";
            validPasswordLength.verify(password);
            verify(validPasswordLength, times(1)).verify(password);
        }

        @Test
        void if_password_has_uppercase_letters() {
            String password = "AJSUIWLE";
            validPasswordLength.verify(password);
            verify(validPasswordLength, times(1)).verify(password);
        }

        @Test
        void if_password_has_uppercase_and_lowercase_letters() {
            String password = "aHrASDeee";
            validPasswordLength.verify(password);
            verify(validPasswordLength, times(1)).verify(password);
        }

        @Test
        void if_password_has_uppercase_lowercase_letters_and_digits() {
            String password = "aEut42Ad";
            validPasswordLength.verify(password);
            verify(validPasswordLength, times(1)).verify(password);
        }

        @Test
        void if_pass_has_special_characters() {
            String password = "14%rqk.";
            validPasswordLength.verify(password);
            verify(validPasswordLength, times(1)).verify(password);
        }
    }

}