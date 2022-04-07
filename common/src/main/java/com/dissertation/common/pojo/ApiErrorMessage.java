package com.dissertation.common.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiErrorMessage {
    private String fieldName;
    private String errorMessageCode;
    private String errorMessage;
}