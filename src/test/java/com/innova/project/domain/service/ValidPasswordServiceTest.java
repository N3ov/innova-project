package com.innova.project.domain.service;

import com.innova.project.domain.service.impl.ValidPasswordServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
class ValidPasswordServiceTest {
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
            String password = "qweA1235";
            Boolean result =validPasswordService.isRegex(password);
            assertEquals(true, result);
        }
    }


    @Nested
    class when_valid_password_length {
        @Test
        void isEnoughLength() {
        }
    }

    @Nested
    class when_valid_password_contain_sequence {
        @Test
        void isContainSequence() {
        }
    }

}