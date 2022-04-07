package com.dissertation.common.exception;

import com.dissertation.common.controller.ApiMessage;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public class SystemException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Getter
    private final List<ApiMessage> messages;

    public SystemException(String message, Throwable cause) {
        super(message, cause);
        this.messages = Arrays.asList(ApiMessage.of(null, message));
    }

    public SystemException(String message) {
        super(message);
        this.messages = Arrays.asList(ApiMessage.of(null, message));
    }

    public SystemException(List<ApiMessage> messages) {
        super(messages == null ? "SystemException" : messages.toString());// error log
        this.messages = messages;
    }

}