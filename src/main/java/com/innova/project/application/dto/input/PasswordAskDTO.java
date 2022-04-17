package com.innova.project.application.dto.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordAskDTO {

    @NotBlank(message = "password can't be null or blank")
    private String password;

}
