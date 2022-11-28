package com.example.irrigation.dtos.request;

import com.example.irrigation.dtos.TimeSlot;
import lombok.Data;

@Data
public class PlotRequest {
    private String length;
    private String breadth;
    private String amountOfWater;
    private TimeSlot timeSlot;

}
