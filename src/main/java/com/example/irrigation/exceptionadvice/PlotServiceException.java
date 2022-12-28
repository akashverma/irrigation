package com.example.irrigation.exceptionadvice;


public class PlotServiceException extends RuntimeException{

    public PlotServiceException(String message) {
        super(message);
    }
}
