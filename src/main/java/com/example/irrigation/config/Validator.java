package com.example.irrigation.config;

import com.example.irrigation.dtos.request.PlotRequest;
import com.example.irrigation.exceptions.PlotServiceException;

public class Validator {
    /**
     * adding provate constructor so no one can instantiate this
     */
    private Validator() {
    }

    public static void validate(PlotRequest plotRequest) {
        if (plotRequest.getLength().isBlank() ||
                plotRequest.getBreadth().isBlank() ||
                plotRequest.getAmountOfWater().isBlank() ||
                plotRequest.getTimeSlot() == null
        ) {
            throw new PlotServiceException("Check plot dimensions, water and Timeslot details!");
        }

    }
}
