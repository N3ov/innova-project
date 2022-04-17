package com.innova.project.application.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.innova.project.application.dto.input.PasswordAskDTO;
import com.innova.project.application.dto.output.PasswordReplyDTO;
import com.innova.project.application.service.PasswordService;
import com.innova.project.core.response.ResponseDTO;
import com.innova.project.core.response.ResponseFailDTO;
import com.innova.project.infrastructure.exception.PasswordValidateException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Objects;

import static com.innova.project.infrastructure.exception.PasswordValidationErrorCode.PASSWORD_MUST_CONSIST_OF_NUMERICAL_DIGITS_AND_LOWERCASE_LETTERS;
import static com.innova.project.infrastructure.exception.PasswordValidationExceptionMessage.PASSWORD_NUMERICAL_DIGITS_AND_LOWERCASE_LETTERS_EXCEPTION;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(PasswordController.class)
@AutoConfigureMockMvc
class PasswordControllerTest {
    @MockBean
    private PasswordService passwordService;

    @Autowired
    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    private static final String BASE_URL = "/password/validation";

    PasswordAskDTO setUpInput(String password) {
        PasswordAskDTO dto = new PasswordAskDTO();
        dto.setPassword(password);
        return dto;
    }

    PasswordReplyDTO setUpOutput() {
        return new PasswordReplyDTO(System.currentTimeMillis(), true);
    }

    @Nested
    @DisplayName(BASE_URL)
    class post_enable {

        @Test
        void if_input_is_null_and_except_not_pass() throws Exception {

            PasswordAskDTO dto = setUpInput(null);
            given(passwordService.verifyPassword(dto)).willThrow(IllegalArgumentException.class);

            mockMvc.perform(
                    post(BASE_URL)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(dto))
            ).andExpect(
                    MockMvcResultMatchers.status().isBadRequest()
            );
            verify(passwordService, times(0)).verifyPassword(dto);
        }

        @Test
        void if_input_is_blank_and_except_not_pass() throws Exception {

            PasswordAskDTO dto = setUpInput("");

            given(passwordService.verifyPassword(dto)).willThrow(IllegalArgumentException.class);

            mockMvc.perform(
                    post(BASE_URL)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(dto))
            ).andExpect(
                    MockMvcResultMatchers.status().isBadRequest()
            );
            verify(passwordService, times(0)).verifyPassword(dto);

        }

        @Test
        void if_password_is_correct_and_except_pass() throws Exception {

            PasswordAskDTO dto = setUpInput("123jeiw");
            PasswordReplyDTO replyDTO = setUpOutput();

            given(passwordService.verifyPassword(dto)).willReturn(replyDTO);

            mockMvc.perform(
                    post(BASE_URL)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(dto))
            ).andExpect(
                    MockMvcResultMatchers.status().isOk()
            ).andExpect(
                    content().json(
                            objectMapper.writeValueAsString(
                                    new ResponseDTO<>(replyDTO)
                            )
                    )
            );
            then(passwordService).should().verifyPassword(dto);
        }

        @Test
        void if_password_has_uppercase_letter_and_except_not_pass() throws Exception {

            PasswordAskDTO dto = setUpInput("123Ajeiw");
            given(passwordService.verifyPassword(dto)).willThrow(PasswordValidateException.class);

            mockMvc.perform(
                    post(BASE_URL)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(dto))
            ).andExpect(
                    MockMvcResultMatchers.status().isBadRequest()
            );
            verify(passwordService).verifyPassword(dto);
        }

        @Test
        void if_password_has_same_letters_and_except_not_pass() throws Exception {

            PasswordAskDTO dto = setUpInput("kkaadajeiw");
            ResponseFailDTO replyDTO = getResponseFailDTO(
                    PASSWORD_MUST_CONSIST_OF_NUMERICAL_DIGITS_AND_LOWERCASE_LETTERS,
                    PASSWORD_NUMERICAL_DIGITS_AND_LOWERCASE_LETTERS_EXCEPTION
            );

            given(passwordService.verifyPassword(dto)).willThrow(PasswordValidateException.class);

            mockMvc.perform(
                    post(BASE_URL)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(dto))
            ).andExpect(
                    MockMvcResultMatchers.status().isBadRequest()
            );

            verify(passwordService).verifyPassword(dto);
        }

        private ResponseFailDTO getResponseFailDTO(Integer code, String message) {
            ResponseFailDTO replyDTO = new ResponseFailDTO();
            replyDTO.setErrorCode(code);
            replyDTO.setMessage(message);
            return replyDTO;
        }

        @Test
        void if_password_has_different_letters_and_except_not_pass() throws Exception {

            PasswordAskDTO dto = setUpInput("kdajeiw");

            given(passwordService.verifyPassword(dto)).willThrow(PasswordValidateException.class);

            mockMvc.perform(
                    post(BASE_URL)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(dto))
            ).andExpect(
                    MockMvcResultMatchers.status().isBadRequest()
            );
            verify(passwordService).verifyPassword(dto);
        }
    }

}