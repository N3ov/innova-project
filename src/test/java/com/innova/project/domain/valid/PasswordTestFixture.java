package com.innova.project.domain.valid;

import java.util.Random;

public class PasswordTestFixture {

    private static final Random random = new Random();

    private static final String NUMERICAL_CHARS = "0123456789";
    private static final String LOWERCASE_CHARS = "abcdefghijklmnopqrstuvwxyz";
    private static final String FULL_CHARS = NUMERICAL_CHARS + LOWERCASE_CHARS;

    public static String createCorrectPassword(Integer len) {

        return generatorPassword(len, FULL_CHARS);
    }

    public static String createNumericalPassword(Integer len) {

        return generatorPassword(len, NUMERICAL_CHARS);
    }

    public static String createLowerLettersPassword(Integer len) {

        return generatorPassword(len, LOWERCASE_CHARS);
    }

    public static String createUpperCaseLettersPassword(Integer len) {

        return createLowerLettersPassword(len).toUpperCase();
    }

    public static String createAllLettersPassword(Integer len) {

        return generatorMixLettersPassword(len, LOWERCASE_CHARS);
    }

    private static String generatorMixLettersPassword(Integer len, String chars) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < len; i++) {
            int randomIndex = random.nextInt(chars.length());
            if(randomIndex % 2 == 0) {
                sb.append(chars.charAt(randomIndex));
            } else {
                sb.append(Character.toUpperCase(chars.charAt(randomIndex)));
            }
        }
        return sb.toString();

    }

    private static String generatorPassword(Integer len, String chars) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < len; i++) {
            int randomIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
        }
        return sb.toString();
    }

    public static String generatorMixturePassword(Integer len) {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            int z = (int) ((Math.random() * 7) % 3);

            // numerical digits
            if (z == 1) {
                sb.append((int) ((Math.random() * 10) + 48));
            // uppercase letters
            } else if (z == 2) {
                sb.append((char) (((Math.random() * 26) + 65)));
            // lowercase letters
            } else {
                sb.append(((char) ((Math.random() * 26) + 97)));
            }
        }
        return sb.toString();
    }

}
