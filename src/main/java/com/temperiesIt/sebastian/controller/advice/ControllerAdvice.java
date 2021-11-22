package com.temperiesIt.sebastian.controller.advice;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(IllegalArgumentException.class)
    public void handleIllegalArgument(HttpServletResponse response) throws IOException {
        response.setStatus(400);
        response.getWriter().println("Los datos enviados no son correctos. Revise e intente nuevamente");
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public void handleEmptyResultDataAccessException(HttpServletResponse response) throws IOException {
        response.setStatus(400);
        response.getWriter().println("No se puede eliminar un id inexistente");
    }

    @ExceptionHandler(NoSuchElementException.class)
    public void handleNoSuchElementException(HttpServletResponse response) throws IOException {
        response.setStatus(400);
        response.getWriter().println("No existe una Persona con este id para actualizar");
    }
}
