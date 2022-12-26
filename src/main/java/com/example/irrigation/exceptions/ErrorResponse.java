package com.example.irrigation.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Data
public class ErrorResponse {
    private String message;
    private HttpStatus httpStatus;
    private Map<String, String> errorResults;
}
