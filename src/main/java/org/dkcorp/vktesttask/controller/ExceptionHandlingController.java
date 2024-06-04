package org.dkcorp.vktesttask.controller;

import org.dkcorp.vktesttask.exception.CustomClientException;
import org.dkcorp.vktesttask.exception.CustomServerException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlingController {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public String serverExceptionHandler(Exception ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(CustomClientException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String clientExceptionHandler(CustomClientException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(CustomServerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String serverExceptionHandler(CustomServerException ex) {
        return ex.getMessage();
    }
}
