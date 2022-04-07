package com.dissertation.common.model.auth.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LoginRequest {
    @JsonProperty("emailAddress")
    private String username;
    private String password;
}