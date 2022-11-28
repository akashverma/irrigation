package com.example.irrigation.exceptions;

public class PlotServiceException extends RuntimeException{

    public PlotServiceException(){
        String message = "Invalid Plot Id";
    }
}
