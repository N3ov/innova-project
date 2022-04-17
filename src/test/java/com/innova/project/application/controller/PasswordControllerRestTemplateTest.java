package com.innova.project.application.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.innova.project.application.dto.input.PasswordAskDTO;
import com.innova.project.application.dto.output.PasswordReplyDTO;
import com.innova.project.application.service.PasswordService;
import com.innova.project.core.response.ResponseDTO;
import com.innova.project.infrastructure.exception.PasswordValidateException;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class PasswordControllerRestTemplateTest {

    private static final ObjectMapper om = new ObjectMapper();
    private static final String BASE_URL = "/password/validation";

    @Autowired
    private TestRestTemplate testRestTemplate;

    @MockBean
    private PasswordService passwordService;

    @Nested
    class test_post {

        private final long currTime = System.currentTimeMillis();

        PasswordAskDTO setup(String password) {
            return new PasswordAskDTO(password);
        }

        PasswordReplyDTO replySetup() {
            return new PasswordReplyDTO(
                    currTime,
                    true
            );
        }

        @Test
        void test_post_correct_password() {
            PasswordAskDTO dto = setup("1o2a3sk");
            PasswordReplyDTO replyDTO = replySetup();

            when(passwordService.verifyPassword(any(PasswordAskDTO.class))).thenReturn(replyDTO);

            ResponseEntity<String> resp = testRestTemplate.postForEntity(BASE_URL, dto, String.class);

            assertEquals(HttpStatus.OK, resp.getStatusCode());
            assertEquals(MediaType.APPLICATION_JSON, resp.getHeaders().getContentType());

            verify(passwordService, times(1)).verifyPassword(dto);
        }

        @Test
        void test_post_invalid_password() {
            PasswordAskDTO dto = setup("1o2A3sk");

            when(passwordService.verifyPassword(any(PasswordAskDTO.class))).thenThrow(PasswordValidateException.class);
            ResponseEntity<String> resp = testRestTemplate.postForEntity(BASE_URL, dto, String.class);
            assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
            assertEquals(MediaType.APPLICATION_JSON, resp.getHeaders().getContentType());
            verify(passwordService, times(1)).verifyPassword(dto);
        }

        @Test
        void test_post_invalid_special_character_password() {
            PasswordAskDTO dto = setup("1o2a!3sk");

            when(passwordService.verifyPassword(any(PasswordAskDTO.class))).thenThrow(PasswordValidateException.class);
            ResponseEntity<String> resp = testRestTemplate.postForEntity(BASE_URL, dto, String.class);
            assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
            assertEquals(MediaType.APPLICATION_JSON, resp.getHeaders().getContentType());
            verify(passwordService, times(1)).verifyPassword(dto);
        }

        @Test
        void test_post_invalid_uppercase_character_password() {
            PasswordAskDTO dto = setup("1o2aS3sk");

            when(passwordService.verifyPassword(any(PasswordAskDTO.class))).thenThrow(PasswordValidateException.class);
            ResponseEntity<String> resp = testRestTemplate.postForEntity(BASE_URL, dto, String.class);
            assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
            assertEquals(MediaType.APPLICATION_JSON, resp.getHeaders().getContentType());
            verify(passwordService, times(1)).verifyPassword(dto);
        }

        @Test
        void test_post_invalid_sequence_password() {
            PasswordAskDTO dto = setup("1o2a3a3sk");

            when(passwordService.verifyPassword(any(PasswordAskDTO.class))).thenThrow(PasswordValidateException.class);
            ResponseEntity<String> resp = testRestTemplate.postForEntity(BASE_URL, dto, String.class);
            assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
            assertEquals(MediaType.APPLICATION_JSON, resp.getHeaders().getContentType());
            verify(passwordService, times(1)).verifyPassword(dto);
        }

        @Test
        void test_post_null() {
            PasswordAskDTO dto = setup(null);

            when(passwordService.verifyPassword(any(PasswordAskDTO.class))).thenThrow(IllegalArgumentException.class);
            ResponseEntity<String> resp = testRestTemplate.postForEntity(BASE_URL, dto, String.class);
            assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
            assertEquals(MediaType.APPLICATION_JSON, resp.getHeaders().getContentType());
            verify(passwordService, times(0)).verifyPassword(dto);
        }

        @Test
        void test_post_blank() {
            PasswordAskDTO dto = setup("");

            when(passwordService.verifyPassword(any(PasswordAskDTO.class))).thenThrow(IllegalArgumentException.class);
            ResponseEntity<String> resp = testRestTemplate.postForEntity(BASE_URL, dto, String.class);
            assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
            assertEquals(MediaType.APPLICATION_JSON, resp.getHeaders().getContentType());
            verify(passwordService, times(0)).verifyPassword(dto);
        }

    }


}
