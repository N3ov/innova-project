package com.innova.project.domain.valid;

import javax.validation.constraints.NotBlank;

public interface PasswordValidation {

    void verify(@NotBlank String password);

}
