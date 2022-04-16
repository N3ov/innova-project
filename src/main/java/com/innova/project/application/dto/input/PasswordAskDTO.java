package com.innova.project.application.dto.input;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PasswordAskDTO {

    @NotBlank(message = "password can't be null or blank")
    private String password;

}
