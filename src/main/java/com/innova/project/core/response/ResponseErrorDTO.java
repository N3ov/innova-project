package com.innova.project.core.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public class ResponseErrorDTO {

    private final Map<String, String> errors = new HashMap<>();

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Setter
    private String message;

    public void addError(String key, String value) {
        errors.put(key, value);
    }

    public ResponseEntity<ResponseErrorDTO> toResponseEntity(HttpStatus status){
        return new ResponseEntity<>(this, status);
    }
}
