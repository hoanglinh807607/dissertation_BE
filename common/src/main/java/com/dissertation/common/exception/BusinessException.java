package com.dissertation.common.exception;

import com.dissertation.common.controller.ApiMessage;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public class BusinessException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Getter
    private final List<ApiMessage> messages;

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
        this.messages = Arrays.asList(ApiMessage.of(null, message));
    }

    public BusinessException(String message) {
        super(message);
        this.messages = Arrays.asList(ApiMessage.of(null, message));
    }

    public BusinessException(List<ApiMessage> messages) {
        super(messages == null ? "BusinessException" : messages.toString());// error log
        this.messages = messages;
    }

}