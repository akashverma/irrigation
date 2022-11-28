package com.example.irrigation;

import com.example.irrigation.dtos.PlotDto;

import java.util.ArrayList;
import java.util.List;

public class IrrigationTestHelper {
    public static List<PlotDto> mockedPlots() {
        List<PlotDto> plots = new ArrayList<>();

        PlotDto plot1 = new PlotDto();
        plot1.setIrrigated(true);
        PlotDto plot2 = new PlotDto();
        plot2.setIrrigated(false);

        plots.add(plot1);
        plots.add(plot2);
        return plots;
    }
}
