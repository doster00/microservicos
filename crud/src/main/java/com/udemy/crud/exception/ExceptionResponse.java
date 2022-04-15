package com.udemy.crud.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ExceptionResponse {
    private LocalDateTime timestamp;
    private String message;
    private String details;
}
