package com.dissertation.common.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Locale;

@Configuration
@PropertySource("classpath:i18n/messages.properties")
public class MessageResource {
    @Autowired
    private MessageSource messageSource;

    public static final String INPUT_REQUIRED = "INPUT_REQUIRED";
    public static final String INPUT_INVALID = "INPUT_INVALID";
    public static final String USER_EMAIL_EXIST = "USER_EMAIL_EXIST";
    public static final String ROLE_DELETE_ASSIGNED = "ROLE_DELETE_ASSIGNED";
    public static final String ROLE_NOT_EXISTS = "ROLE_NOT_EXISTS";

    public String getMessage(String key) {
        return this.messageSource.getMessage(key, null, Locale.ENGLISH);
    }
}
