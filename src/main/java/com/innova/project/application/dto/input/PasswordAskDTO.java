package com.innova.project.application.dto.input;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PasswordAskDTO {

    @NonNull
    @NotBlank
    private String password;

}
