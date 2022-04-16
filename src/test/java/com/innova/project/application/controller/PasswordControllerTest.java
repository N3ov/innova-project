package com.innova.project.application.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.innova.project.application.dto.input.PasswordAskDTO;
import com.innova.project.application.dto.output.PasswordReplyDTO;
import com.innova.project.application.service.PasswordService;
import com.innova.project.core.response.ResponseDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(PasswordController.class)
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
            PasswordReplyDTO replyDTO = setUpOutput();

            given(passwordService.verifyPassword(dto)).willReturn(replyDTO);

            mockMvc.perform(
                    post(BASE_URL)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(dto))
            ).andExpect(
                    MockMvcResultMatchers.status().isBadRequest()
            );
        }

        @Test
        void if_input_is_blank_and_except_not_pass() throws Exception {

            PasswordAskDTO dto = setUpInput("");
            PasswordReplyDTO replyDTO = setUpOutput();

            given(passwordService.verifyPassword(dto)).willReturn(replyDTO);

            mockMvc.perform(
                    post(BASE_URL)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(dto))
            ).andExpect(MockMvcResultMatchers.status().isBadRequest());
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
                    ).andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(
                            content().json(
                                    objectMapper.writeValueAsString(
                                            new ResponseDTO<>(replyDTO)
                                    )
                            )
                    );
        }

        @Test
        void if_password_has_uppercase_letter_and_except_not_pass() throws Exception {

            PasswordAskDTO dto = setUpInput("123Ajeiw");
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
        }

        @Test
        void if_password_has_same_letters_and_except_not_pass() throws Exception {

            PasswordAskDTO dto = setUpInput("kkaadajeiw");
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
        }

        @Test
        void if_password_has_different_letters_and_except_not_pass() throws Exception {

            PasswordAskDTO dto = setUpInput("kdajeiw");
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
        }
    }

}