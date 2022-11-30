package com.example.irrigation;

import com.example.irrigation.dtos.PlotDto;
import com.example.irrigation.dtos.TimeSlot;
import com.example.irrigation.dtos.request.PlotRequest;
import com.example.irrigation.entity.Plot;

import java.util.ArrayList;
import java.util.List;

import static com.example.irrigation.TestConstants.SAVED_PLOT_ID;
import static com.example.irrigation.TestConstants.TEST_AMT_WATER;
import static com.example.irrigation.TestConstants.TEST_BREADTH;
import static com.example.irrigation.TestConstants.TEST_LENGTH;
import static com.example.irrigation.TestConstants.TEST_PLOT_ID;

public class IrrigationTestHelper {

    public static Plot mockSavedPlot() {
        Plot plot = new Plot();
        plot.setId(SAVED_PLOT_ID);
        plot.setTimeSlot(TimeSlot.MORNING);
        plot.setIrrigated(false);
        plot.setAmountOfWater(TEST_AMT_WATER);
        plot.setBreadth(TEST_BREADTH);
        plot.setLength(TEST_LENGTH);
        return plot;
    }

    public static List<PlotDto> mockPlotDtos() {
        List<PlotDto> plots = new ArrayList<>();

        PlotDto plot1 = new PlotDto();
        plot1.setIrrigated(true);
        PlotDto plot2 = new PlotDto();
        plot2.setIrrigated(false);

        plots.add(plot1);
        plots.add(plot2);
        return plots;
    }

    public static List<Plot> mockPlots() {
        List<Plot> plots = new ArrayList<>();

        Plot plot1 = new Plot();
        plot1.setId(Long.valueOf(TEST_PLOT_ID));
        plot1.setIrrigated(true);

        Plot plot2 = new Plot();
        plot2.setId(Long.valueOf("101"));
        plot2.setIrrigated(true);


        plots.add(plot1);
        plots.add(plot2);
        return plots;
    }

    public static PlotRequest plotRequest(){
        PlotRequest plotRequest = new PlotRequest();
        plotRequest.setLength("20");
        plotRequest.setBreadth("10");
        plotRequest.setAmountOfWater("150");
        plotRequest.setTimeSlot(TimeSlot.MORNING);
        return plotRequest;
    }
}
