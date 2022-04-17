package com.innova.project.domain.valid;

import com.innova.project.infrastructure.exception.PasswordValidateException;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static com.innova.project.domain.valid.PasswordTestFixture.*;
import static com.innova.project.domain.valid.PasswordTestFixture.generatorMixturePassword;
import static com.innova.project.infrastructure.exception.PasswordValidationErrorCode.PASSWORD_LENGTH_DOES_NOT_MATCH;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class ValidPasswordLengthTest {
    @Mock
    private ValidPasswordLength validPasswordLength;

    private static final String EXCEPTION = "the password length must between 5 and 12";


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Nested
    @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
    class when_valid_password_is_digits_or_lowercase_letters_and_verify_pass {
        @Test
        void if_password_are_digits_and_length_between_5_and_12() {
            String password = createNumericalPassword(7);
            passAssertion(password);
        }

        @Test
        void if_password_are_lowercase_letter_and_length_between_5_and_12() {
            String password = createLowerLettersPassword(6);
            passAssertion(password);

        }

        @Test
        void if_password_has_lowercase_letters_and_digit_and_length_between_5_and_12() {
            String password = createCorrectPassword(8);
            passAssertion(password);
        }

        @Test
        void if_password_has_uppercase_letters_and_length_between_5_and_12() {
            String password = createUpperCaseLettersPassword(6);
            passAssertion(password);
        }

        @Test
        void if_password_has_uppercase_and_lowercase_letters_and_length_between_5_and_12() {
            String password = createAllLettersPassword(7);
            passAssertion(password);
        }

        @Test
        void if_password_has_uppercase_lowercase_letters_and_digits_and_length_between_5_and_12() {
            String password = generatorMixturePassword(7);
            passAssertion(password);
        }

        @Test
        void if_pass_has_special_characters_and_length_between_5_and_12() {
            String password = generatorMixturePassword(6) + ".";
            passAssertion(password);
        }

        private void passAssertion(String password) {
            doNothing().when(validPasswordLength).verify(password);
            validPasswordLength.verify(password);
            verify(validPasswordLength, times(1)).verify(password);
        }
    }

    @Nested
    @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
    class when_valid_password_is_digits_or_lowercase_letters_and_verify_not_pass {

        @Test
        void if_password_are_digits_and_length_not_between_5_and_12() {
            String password = createNumericalPassword(3);
            errorAssertion(password);
        }

        @Test
        void if_password_are_lowercase_letter_and_length_not_between_5_and_12() {
            String password = createLowerLettersPassword(3);
            errorAssertion(password);
        }

        @Test
        void if_password_has_lowercase_letters_and_digit_and_length_not_between_5_and_12() {
            String password = createCorrectPassword(8);
            errorAssertion(password);
        }

        @Test
        void if_password_has_uppercase_letters_and_length_between_5_and_12() {
            String password = createUpperCaseLettersPassword(6);
            errorAssertion(password);
        }

        @Test
        void if_password_has_uppercase_and_lowercase_letters_and_length_between_5_and_12() {
            String password = createAllLettersPassword(7);
            errorAssertion(password);
        }

        @Test
        void if_password_has_uppercase_lowercase_letters_and_digits_and_length_between_5_and_12() {
            String password = generatorMixturePassword(7);
            errorAssertion(password);
        }

        @Test
        void if_pass_has_special_characters_and_length_between_5_and_12() {
            String password = generatorMixturePassword(6) + ".";
            errorAssertion(password);
        }
    }

    private void errorAssertion(String password) {
        doThrow(new PasswordValidateException(
                        PASSWORD_LENGTH_DOES_NOT_MATCH,
                        EXCEPTION
                )
        ).when(validPasswordLength).verify(password);

        try {
            validPasswordLength.verify(password);
            fail();
        } catch (RuntimeException e) {
            assertEquals(EXCEPTION, e.getMessage());
        }

        verify(validPasswordLength, times(1)).verify(password);

        assertThrows(
                PasswordValidateException.class,
                () -> validPasswordLength.verify(password),
                EXCEPTION
        );
    }
}

