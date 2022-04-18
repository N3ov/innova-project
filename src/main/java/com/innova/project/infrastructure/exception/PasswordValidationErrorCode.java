package com.innova.project.infrastructure.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PasswordValidationErrorCode {

    public static final Integer PASSWORD_CAN_NOT_BE_NULL_OR_BLANK = 0;
    public static final Integer PASSWORD_MUST_CONSIST_OF_NUMERICAL_DIGITS_AND_LOWERCASE_LETTERS = 1;
    public static final Integer PASSWORD_HAS_REPEATED_SEQUENCE = 2;
    public static final Integer PASSWORD_LENGTH_DOES_NOT_MATCH = 3;
}
