package com.example.irrigation.dtos.request;

import com.example.irrigation.dtos.TimeSlot;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@ApiModel("The model representing a plot request")
public class PlotRequest {

    @NotBlank(message = "length of plot is mandatory")
    @ApiModelProperty(value = "length of the plot", name = "length", dataType = "String", example = "10 units")
    private String length;

    @NotBlank(message = "breadth of plot is mandatory")
    @ApiModelProperty(value = "breadth of the plot", name = "breadth", dataType = "String", example = "20 units")
    private String breadth;

    @Min(value = 10, message = "Minimum 10 litres required")
    @ApiModelProperty(value = "required amount of water for the plot. Keeping Minimum 10 (only for validation)", name = "amountOfWater", dataType = "String", example = "100L")
    private String amountOfWater;

    @ApiModelProperty(value = "requested timeslot for configuring the plot", name = "timeSlot", dataType = "TimeSlot", example = "MORNING")
    private TimeSlot timeSlot;

}
