package com.innova.project.application.dto.output;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PasswordReplyDTO {

    private Long validTime;
    private Boolean isPass;

}
