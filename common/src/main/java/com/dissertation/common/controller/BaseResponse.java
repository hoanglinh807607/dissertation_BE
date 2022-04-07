package com.dissertation.common.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@SuperBuilder
public class BaseResponse {
    boolean ok;
    List<ApiMessage> messages;

    public static List<ApiMessage> getInternalServerErrorMessage() {
        return Arrays.asList(ApiMessage.of("", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()));
    }
}