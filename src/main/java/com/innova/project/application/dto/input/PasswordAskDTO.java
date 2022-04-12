package com.innova.project.application.dto.input;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PasswordAskDTO {

    private String password;
    private Integer encrypt;

}
