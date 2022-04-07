package com.dissertation.common.model.auth.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
    private Integer userId;
    private String emailAddress;
    private String phoneNumber;
    private String fullName;
    private List<String> roles;
}