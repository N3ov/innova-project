package com.innova.project.domain.valid;

import com.innova.project.infrastructure.exception.PasswordValidateException;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static com.innova.project.domain.valid.PasswordTestFixture.*;
import static com.innova.project.infrastructure.exception.PasswordValidationErrorCode.PASSWORD_MUST_CONSIST_OF_NUMERICAL_DIGITS_AND_LOWERCASE_LETTERS;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class ValidNumericalAndLettersTest {
    @Mock
    private ValidNumericalAndLetters validNumericalAndLetters;

    private static final String EXCEPTION = "the password must consist of numerical digits and lowercase letters";


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Nested
    @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
    class when_valid_password_is_numerical_digits_and_lowercase_letters {
        @Test
        void if_password_all_digits() {
            String password = createNumericalPassword(7);
            errorException(password);
        }

        @Test
        void if_password_all_lower_case_character() {
            String password = createLowerLettersPassword(7);
            errorException(password);
        }

        @Test
        void if_password_has_lower_case_character_and_digits() {
            String password = createCorrectPassword(7);
            doNothing().when(validNumericalAndLetters).verify(password);
            validNumericalAndLetters.verify(password);
            verify(validNumericalAndLetters, times(1)).verify(password);
        }

        @Test
        void if_password_has_upper_case_character() {
            String password = generatorMixturePassword(7);
            errorException(password);
        }

        @Test
        void if_password_has_special_character() {
            String password = "!32kdi9";
            errorException(password);
        }

        private void errorException(String password) {
            doThrow(new PasswordValidateException(
                            PASSWORD_MUST_CONSIST_OF_NUMERICAL_DIGITS_AND_LOWERCASE_LETTERS,
                            EXCEPTION
                    )
            ).when(validNumericalAndLetters).verify(password);

            try {
                validNumericalAndLetters.verify(password);
                fail();
            } catch (RuntimeException e) {
                assertEquals(EXCEPTION, e.getMessage());
            }

            verify(validNumericalAndLetters, times(1)).verify(password);

            assertThrows(
                    PasswordValidateException.class,
                    () -> validNumericalAndLetters.verify(password),
                    EXCEPTION
            );
        }
    }
}