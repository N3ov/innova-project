package com.innova.project.application.controller;

import com.innova.project.application.dto.input.PasswordAskDTO;
import com.innova.project.application.dto.output.PasswordReplyDTO;
import com.innova.project.application.service.PasswordService;
import com.innova.project.core.response.ResponseDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Tag(name = "valid password")
@RestController
@RequiredArgsConstructor
@RequestMapping("/password")
public class PasswordController {

    private final PasswordService validPasswordService;

    @PostMapping("/validation")
    public ResponseDTO<PasswordReplyDTO> validPassword(
            @Valid @RequestBody PasswordAskDTO dto
    ) {
        return new ResponseDTO<>(
                validPasswordService.isPass(dto)
        );
    }

}
