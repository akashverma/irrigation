package com.example.irrigation.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionAdvice {


    /*@ExceptionHandler(PaymentGatewayException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun invalidRequestPayloadException(e: PaymentGatewayException): ResponseEntity<ErrorResponse> {
        log.error("Handling invalidMethodArgumentException", e)
        val errorResponse = ErrorResponse(ErrorCode.VALIDATION_ERROR.code, HttpStatus.BAD_REQUEST, e.message)
        return ResponseEntity.badRequest().body(errorResponse)
    }*/

    @ExceptionHandler(PlotServiceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<ErrorResponse> invalidRequest(PlotServiceException e){
        ErrorResponse errorResponse = new ErrorResponse("400", e.getMessage());
        return ResponseEntity.badRequest().body(errorResponse);
    }
}
