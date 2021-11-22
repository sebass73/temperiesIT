package com.temperiesIt.sebastian.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(IllegalArgumentException.class)

    public void handleIllegalArgument(IllegalArgumentException exception, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }
}
