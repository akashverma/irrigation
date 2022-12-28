package com.example.irrigation.services;

import com.example.irrigation.dtos.PlotDto;
import com.example.irrigation.dtos.request.PlotRequest;
import com.example.irrigation.entity.Plot;
import com.example.irrigation.exceptionadvice.PlotServiceException;
import com.example.irrigation.repository.PlotRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PlotServiceImpl implements PlotService {

    @Autowired
    private PlotRepository plotRepository;

    @Override
    public Plot addPlot(PlotRequest request) {
        log.info("Adding new plot of land");

        Plot plot = new Plot();
        plot.setIrrigated(false);
        plot.setAmountOfWater(request.getAmountOfWater());
        plot.setTimeSlot(request.getTimeSlot());

        plot.setLength(request.getLength());
        plot.setBreadth(request.getBreadth());
        Plot savedPlot = plotRepository.save(plot);
        log.info("New plot saved: [{}]", savedPlot);
        return savedPlot;
    }

    @Override
    public List<PlotDto> fetchPlots() {
        List<Plot> plots = (List<Plot>) plotRepository.findAll();
        log.info("Fetched all Plots currently configured with size: {} ", plots.size());
        return convertToPlotDto(plots);
    }

    @Override
    public Plot updatePlot(Long id, PlotRequest request) {
        Optional<Plot> plotOptional = plotRepository.findById(id);

        if (plotOptional.isPresent()) {
            Plot plot = plotOptional.get();
            plot.setLength(request.getLength());
            plot.setBreadth(request.getBreadth());
            return plotRepository.save(plot);
        } else {
            throw new PlotServiceException("Invalid Plot id: " + id);
        }
    }

    List<PlotDto> convertToPlotDto(List<Plot> plots) {
        List<PlotDto> plotDtos = new ArrayList<>();
        for (Plot p : plots) {
            PlotDto plotDto = new PlotDto();
            plotDto.setPlotId(p.getId().toString());
            plotDto.setBreadth(p.getBreadth());
            plotDto.setLength(p.getLength());
            plotDto.setTimeSlot(p.getTimeSlot());
            plotDto.setAmountOfWater(p.getAmountOfWater());
            plotDto.setIrrigated(p.isIrrigated());
            plotDtos.add(plotDto);
        }
        return plotDtos;
    }


}
