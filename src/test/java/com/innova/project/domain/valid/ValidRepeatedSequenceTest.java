package com.innova.project.domain.valid;

import com.innova.project.infrastructure.exception.PasswordValidateException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static com.innova.project.infrastructure.exception.PasswordValidationErrorCode.PASSWORD_HAS_REPEATED_SEQUENCE;
import static com.innova.project.infrastructure.exception.PasswordValidationExceptionMessage.PASSWORD_REPEATED_SEQUENCE_EXCEPTION;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class ValidRepeatedSequenceTest {

    @Mock
    private ValidRepeatedSequence validRepeatedSequence;

    private static final String EXCEPTION = PASSWORD_REPEATED_SEQUENCE_EXCEPTION;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Nested
    class when_valid_password_contain_sequence {
        @Test
        void if_password_no_repeated_subString() {
            String password = "a123wish";
            doNothing().when(validRepeatedSequence).verify(password);
            validRepeatedSequence.verify(password);
            verify(validRepeatedSequence, times(1)).verify(password);
        }
    }

    @Test
    void if_password_has_repeated_subString() {
        String password = "123123iao";
        errorException(password);
    }

    private void errorException(String password) {
        doThrow(new PasswordValidateException(
                        PASSWORD_HAS_REPEATED_SEQUENCE,
                        EXCEPTION
                )
        ).when(validRepeatedSequence).verify(password);

        try {
            validRepeatedSequence.verify(password);
            fail();
        } catch (RuntimeException e) {
            assertEquals(EXCEPTION, e.getMessage());
        }

        verify(validRepeatedSequence, times(1)).verify(password);

        assertThrows(
                PasswordValidateException.class,
                () -> validRepeatedSequence.verify(password),
                EXCEPTION
        );
    }
}