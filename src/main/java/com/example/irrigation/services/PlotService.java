package com.example.irrigation.services;

import com.example.irrigation.dtos.PlotDto;
import com.example.irrigation.dtos.request.PlotRequest;
import com.example.irrigation.entity.Plot;

import java.util.List;

public interface PlotService {
    /**
     * add new plot
     *
     * @param plotRequest - incoming plot request
     * @return
     */
    Plot addPlot(PlotRequest plotRequest);

    /**
     * fetch existing plots
     *
     * @return
     */
    List<PlotDto> fetchPlots();

    /**
     * update existing plot
     *
     * @param id          - incoming id of existing plot
     * @param plotRequest - incoming plot request
     * @return
     */
    Plot updatePlot(Long id, PlotRequest plotRequest);
}
