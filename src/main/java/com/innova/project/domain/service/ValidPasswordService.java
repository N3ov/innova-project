package com.innova.project.domain.service;

import javax.validation.constraints.NotBlank;

public interface ValidPasswordService {

    Boolean isRegex(@NotBlank String password);

    Boolean isEnoughLength(@NotBlank String password);

    Boolean isNotRepeatedSequence(@NotBlank String password);

    Boolean isNumericOrLowerLetters(@NotBlank String password);

}
