package com.innova.project.domain.service.impl;

import com.innova.project.domain.service.impl.ValidPasswordServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
class ValidPasswordServiceImplTest {
    @InjectMocks
    ValidPasswordServiceImpl validPasswordService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Nested
    @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
    class when_valid_password_is_regex {
        @Test
        void if_password_all_digits() {
            String password = "598962";
            System.out.println(password);
            Boolean result = validPasswordService.isRegex(password);
            assertEquals(false, result);
        }

        @Test
        void if_password_all_lower_case_character() {
            String password = "asdqweqwefff";
            Boolean result = validPasswordService.isRegex(password);
            assertEquals(false, result);
        }

        @Test
        void if_password_has_lower_case_character_and_digits() {
            String password = "a123wish";
            Boolean result = validPasswordService.isRegex(password);
            assertEquals(true, result);
        }

        @Test
        void if_password_has_upper_case_character() {
            String password = "qwe1A235";
            Boolean result = validPasswordService.isRegex(password);
            assertEquals(true, result);
        }

        @Test
        void if_password_has_special_character() {
            String password = "!32kdi9";
            assertEquals(true, validPasswordService.isRegex(password));
        }

    }

    @Nested
    class when_valid_password_length {
        String setUp(int length) {
            StringBuilder password = new StringBuilder();
            for (int i = 0; i < length; i++) {
                password.append(Character.toChars(i));
            }
            return password.toString();
        }
        @Test
        void if_password_length_over_12() {
            String password = setUp(14);
            assertEquals(false, validPasswordService.isEnoughLength(password));
        }
        @Test
        void if_password_length_less_then_5() {
            String password = setUp(4);
            assertEquals(false,validPasswordService.isEnoughLength(password));
        }
        @Test
        void if_password_length_in_5_to_12(){
            String pd5 = setUp(5);
            String pd12 = setUp(12);
            assertEquals(true, validPasswordService.isEnoughLength(pd5));
            assertEquals(true, validPasswordService.isEnoughLength(pd12));
        }
    }

    @Nested
    class when_valid_password_contain_sequence {
        @Test
        void if_password_no_repeated_subString() {
            String password = "a123wish";
            assertEquals(true, validPasswordService.isNotRepeatedSequence(password));
        }

        @Test
        void if_password_has_repeated_subString(){
            String password = "123123iao";
            assertEquals(false, validPasswordService.isNotRepeatedSequence(password));
        }
    }

    @Nested
    @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
    class when_valid_password_is_digits_or_lowercase_letters {
        @Test
        void if_password_are_digits() {
            String password = "12312444";
            Boolean result = validPasswordService.isNumericOrLowerLetters(password);
            assertEquals(true, result);
        }

        @Test
        void if_password_are_lowercase_letter() {
            String password = "asdbbsdad";
            Boolean result = validPasswordService.isNumericOrLowerLetters(password);
            assertEquals(true, result);
        }

        @Test
        void if_password_has_lowercase_letters_and_digit() {
            String password = "a139b24";
            Boolean result = validPasswordService.isNumericOrLowerLetters(password);
            assertEquals(true, result);
        }

        @Test
        void if_password_has_uppercase_letters() {
            String password = "AJSUIWLE";
            assertEquals(false, validPasswordService.isNumericOrLowerLetters(password));
        }

        @Test
        void if_password_has_uppercase_and_lowercase_letters() {
            String password = "aHrASDeee";
            assertEquals(false, validPasswordService.isNumericOrLowerLetters(password));
        }

        @Test
        void if_password_has_uppercase_lowercase_letters_and_digits() {
            String password = "aEut42Ad";
            assertEquals(false, validPasswordService.isNumericOrLowerLetters(password));
        }

        @Test
        void if_pass_has_special_characters() {
            String password = "14%rqk.";
            assertEquals(false, validPasswordService.isNumericOrLowerLetters(password));
        }
    }

}