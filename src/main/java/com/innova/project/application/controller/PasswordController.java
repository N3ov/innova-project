package com.innova.project.application.controller;

import com.innova.project.application.dto.input.PasswordAskDTO;
import com.innova.project.application.dto.output.PasswordReplyDTO;
import com.innova.project.application.service.PasswordService;
import com.innova.project.core.response.ResponseDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "valid password")
@RestController("/password/validation")
@RequiredArgsConstructor
public class PasswordController {

    private final PasswordService validPasswordService;

    @PostMapping()
    public ResponseDTO<PasswordReplyDTO> validPassword(
            @RequestBody PasswordAskDTO dto
    ) {
        return new ResponseDTO<>(
                validPasswordService.isPass(dto)
        );
    }

}
