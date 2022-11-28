package com.example.irrigation.services;

import com.example.irrigation.dtos.PlotDto;
import com.example.irrigation.dtos.request.PlotRequest;
import com.example.irrigation.entity.Plot;

import java.util.List;

public interface PlotService {
    Plot addPlot(PlotRequest plotRequest);

    List<PlotDto> fetchPlots();

    Plot updatePlot(Long id, PlotRequest plotRequest);
}
