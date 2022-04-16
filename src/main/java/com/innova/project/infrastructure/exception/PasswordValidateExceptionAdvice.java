package com.innova.project.infrastructure.exception;

import com.innova.project.core.response.ResponseFailDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PasswordValidateExceptionAdvice {

    @ExceptionHandler(PasswordValidateException.class)
    public final ResponseEntity<ResponseFailDTO> handleException(PasswordValidateException e) {
        ResponseFailDTO dto = new ResponseFailDTO();
        dto.setErrorCode(e.getErrorCode());
        dto.setMessage(e.getMessage());
        return dto.toResponseEntity(HttpStatus.BAD_REQUEST);
    }

}
