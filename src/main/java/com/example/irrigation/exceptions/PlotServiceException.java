package com.example.irrigation.exceptions;

public class PlotServiceException extends RuntimeException{

    final String message;

    public PlotServiceException(String message){
        this.message = message;
    }
}
