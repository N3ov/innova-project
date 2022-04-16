package com.innova.project.core.swagger;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class ResultFails {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String traceId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String code;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String message;

    ResultFails() {
    }

    public void clear() {
        this.traceId = null;
        this.message = null;
    }

    public ResultFails traceId(String traceId) {
        this.traceId = traceId;
        return this;
    }

    public ResultFails code(String code) {
        this.code = code;
        return this;
    }

    public ResultFails message(String message) {
        this.message = message;
        return this;
    }

    public ResponseEntity<ResultFails> out(HttpStatus status) {
        return new ResponseEntity<>(this, status);
    }

}
