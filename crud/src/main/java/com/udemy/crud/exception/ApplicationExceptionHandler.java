package com.udemy.crud.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handlerBadRequestException(Exception ex, WebRequest webRequest) {
        ExceptionResponse response = new ExceptionResponse(LocalDateTime.now(), ex.getMessage(), webRequest.getDescription(false));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
