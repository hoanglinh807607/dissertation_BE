package com.dissertation.common.exception;

import com.dissertation.common.controller.ApiMessage;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public class ClientException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Getter
    private final List<ApiMessage> messages;

    public ClientException(String message, Throwable cause) {
        super(message, cause);
        this.messages = Arrays.asList(ApiMessage.of(null, message));
    }

    public ClientException(String message) {
        super(message);
        this.messages = Arrays.asList(ApiMessage.of(null, message));
    }

    public ClientException(final List<ApiMessage> messages) {
        super(messages == null ? "ClientException" : messages.toString());// error log
        this.messages = messages;
    }

    public ClientException(ApiMessage message) {
        super(message == null ? "ClientException" : message.toString());// error log
        this.messages = Arrays.asList(message);
    }

}