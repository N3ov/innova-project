package com.innova.project.core.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Schema
public class ResponseDTO<T> implements Serializable {

    private final T data;

    public ResponseDTO(T data) {
        this.data = data;
    }

}
