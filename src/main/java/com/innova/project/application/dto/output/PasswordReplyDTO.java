package com.innova.project.application.dto.output;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PasswordReplyDTO {

    private Boolean validStatus = false;
    private Integer encrypt;
    private String password;
}
