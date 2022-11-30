package com.example.irrigation.config;

import com.example.irrigation.dtos.request.PlotRequest;
import com.example.irrigation.exceptions.PlotServiceException;

public class Validator {
    public static void validate(PlotRequest plotRequest) {
        if (plotRequest.getBreadth().isBlank()) {
            throw new PlotServiceException("breadth is mandatory");
        }

        //todo add more validations
    }
}
