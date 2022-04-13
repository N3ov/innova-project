package com.innova.project.domain.service;

import javax.validation.constraints.NotBlank;

public interface ValidPasswordService {

    Boolean isRegex(String password);

    Boolean isEnoughLength(@NotBlank String password);

    Boolean isContainSequence(@NotBlank String password);

}
