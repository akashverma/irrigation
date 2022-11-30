package com.example.irrigation.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(PlotServiceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> invalidRequest(PlotServiceException e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(e.message);
        errorResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
        return ResponseEntity.badRequest().body(errorResponse);
    }
}
