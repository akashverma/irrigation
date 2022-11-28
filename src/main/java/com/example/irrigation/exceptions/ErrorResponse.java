package com.example.irrigation.exceptions;


public class ErrorResponse {
    private String errorCode;
    private String errorMessage;

    ErrorResponse(String errorCode, String errorMessage){
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
