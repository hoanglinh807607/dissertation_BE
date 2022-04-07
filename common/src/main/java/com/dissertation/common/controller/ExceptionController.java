package com.dissertation.common.controller;

import com.dissertation.common.exception.ClientException;
import com.mongodb.lang.Nullable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ControllerAdvice
@RestControllerAdvice
@Slf4j
public class ExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ClientException.class})
    @ResponseBody
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public BaseResponse handleClientException(HttpServletRequest request, ClientException ex) {
        return BaseResponse.of(false, ex.getMessages());
    }

    @ExceptionHandler(value = {BadCredentialsException.class, DisabledException.class, LockedException.class})
    @ResponseBody
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public BaseResponse handleBadCredentialsException(HttpServletRequest request, Throwable ex) {
        List<ApiMessage> list = new ArrayList<>();
        if (ex instanceof BadCredentialsException) {
            list.add(ApiMessage.of(null, "Invalid email or password. Please try again."));
        }
        if (ex instanceof LockedException) {
            list.add(ApiMessage.of(null, "Your account has not been activated. Please contact with Super Admin."));
        }
        if (ex instanceof DisabledException) {
            list.add(ApiMessage.of(null, "Your account has been disabled. Please contact with Super Admin."));
        }
        return BaseResponse.of(false, list);
    }


    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status,
                                                             WebRequest request) {
        log.error("Error: [{}]", ex);
        List<ApiMessage> list = Arrays.asList(ApiMessage.of("global", ex.getMessage()));
        return ResponseEntity.badRequest().body(BaseResponse.of(false, list));
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public BaseResponse handleUnknownException(Exception ex) {
        log.error("Error: [{}]", ex);
        List<ApiMessage> list = Arrays.asList(ApiMessage.of("global", ex.getMessage()));
        return BaseResponse.of(false, list);
    }
}