package com.innova.project.application.dto.output;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PasswordReplyDTO {

    private Integer validStatus;
    private Integer encrypt;
    private String password;
}
