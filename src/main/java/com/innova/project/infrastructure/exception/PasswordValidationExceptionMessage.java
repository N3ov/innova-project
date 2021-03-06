package com.innova.project.infrastructure.exception;

public class PasswordValidationExceptionMessage {
    public final static String PASSWORD_NUMERICAL_DIGITS_AND_LOWERCASE_LETTERS_EXCEPTION = "The password must consist of numerical digits and lowercase letters";
    public final static String PASSWORD_REPEATED_SEQUENCE_EXCEPTION = "The password must can't contains the same sequence.";
    public final static String PASSWORD_LENGTH_DOES_NOT_MATCH_EXCEPTION = "The password length must between 5 and 12";

    public final static String PASSWORD_CAN_NOT_BLANK_OR_NULL = "The password can't be null or blank.";
}
