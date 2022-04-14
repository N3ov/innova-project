package com.innova.project.infrastructure.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

@Getter
public class ResultErrors {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String traceId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String code;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Schema(example = "{\"param1\":\"error message\",\"param2\":\"error message\"}")
    Map<String, String> errors;

    ResultErrors(){}

    public void clear(){
        this.traceId = null;
        this.message = null;
        this.errors = null;
    }

    public ResultErrors traceId(String traceId) {
        this.traceId = traceId;
        return this;
    }

    public ResultErrors code(String code) {
        this.code = code;
        return this;
    }

    public ResultErrors message(String message) {
        this.message = message;
        return this;
    }

    public ResultErrors errors(String key, String value) {
        if (errors == null) errors = new HashMap<>();
        errors.put(key, value);
        return this;
    }

    public ResponseEntity<ResultErrors> out(HttpStatus status) {
        return new ResponseEntity<>(this, status);
    }


}
