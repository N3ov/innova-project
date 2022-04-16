package com.innova.project.core.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class ResponseFailDTO implements Serializable {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer errorCode;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    public ResponseEntity<ResponseFailDTO> toResponseEntity(HttpStatus status) {
        return new ResponseEntity<>(this, status);
    }

}
