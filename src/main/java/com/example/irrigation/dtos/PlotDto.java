package com.example.irrigation.dtos;

import lombok.Data;

@Data
public class PlotDto {
    private String plotId;
    private String length;
    private String breadth;


    private String amountOfWater;

    private boolean isIrrigated;

    private TimeSlot timeSlot;
}
