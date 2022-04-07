package com.dissertation.common.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Collections;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GeneralApiResponse<T> implements Serializable {

    private String statusCode;
    private int status;
    private T result;
    private String message;
    private Object errors;

    public GeneralApiResponse(String statusCode, int status) {
        this.statusCode = statusCode;
        this.status = status;
    }

    public GeneralApiResponse(String statusCode, int status, T result, String message) {
        this.statusCode = statusCode;
        this.status = status;
        this.result = result;
        this.message = message;
    }

    public GeneralApiResponse(String statusCode, int status, T result) {
        this.statusCode = statusCode;
        this.status = status;
        this.result = result;
    }

    public static GeneralApiResponse<String> createErrorResponseWithCode(String errorCode) {
        return new GeneralApiResponse<String>(errorCode, 1, null);
    }

    public static GeneralApiResponse<String> createErrorResponseWithErrorObjects(String statusCode, int status, ApiErrorMessage errors) {
        GeneralApiResponse<String> generalApiResponse = new GeneralApiResponse<>(statusCode, status);
        generalApiResponse.setErrors(Collections.singletonList(errors));
        return generalApiResponse;
    }
}

