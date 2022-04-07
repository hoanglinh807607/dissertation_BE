package com.dissertation.common.model.auth.response;

import com.dissertation.common.controller.BaseResponse;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@SuperBuilder
public class SignupResponse extends BaseResponse {
    private String message;
}