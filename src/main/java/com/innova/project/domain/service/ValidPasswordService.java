package com.innova.project.domain.service;

import javax.validation.constraints.NotBlank;

public interface ValidPasswordService {

    Boolean isDigitsOrLowerCaseLetter(String password);

    Boolean isEnoughLength(@NotBlank String password);

    Boolean isContainSequence(@NotBlank String password);

}
