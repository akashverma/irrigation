package com.example.irrigation.dtos.request;

import com.example.irrigation.dtos.TimeSlot;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("The model representing plot request")
public class PlotRequest {
    private String length;
    private String breadth;
    private String amountOfWater;
    private TimeSlot timeSlot;

}
