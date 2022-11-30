package com.example.irrigation.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ErrorResponse {
    private String message;
    private HttpStatus httpStatus;
}
