package com.example.irrigation.controllers;


import com.example.irrigation.dtos.PlotDto;
import com.example.irrigation.dtos.request.PlotRequest;
import com.example.irrigation.entity.Plot;
import com.example.irrigation.scheduler.IrrigationScheduler;
import com.example.irrigation.services.PlotService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1")
public class PlotController {

    @Autowired
    private IrrigationScheduler irrigationScheduler;

    @Autowired
    private PlotService plotService;

    /**
     * add a new plot of land
     *
     * @param plotRequest
     */
    @PostMapping("/plot")
    @ApiOperation(value = "API to add a new Plot of Land", notes = "Choose Slot from MORNING, NOON, EVENING")
    public ResponseEntity<Plot> addPlot(@RequestBody PlotRequest plotRequest) {
        log.info("request received to add a new PlotRequest of Land: [{}]", plotRequest);
        return ResponseEntity.accepted().body(plotService.addPlot(plotRequest));
    }

    /**
     * configure or update an existing plot
     *
     * @param id
     * @param plotRequest
     * @return
     */
    @PatchMapping("/plot/{id}")
    @ApiOperation(value = "update an existing Plot of Land", notes = "provide the existing plot id to update")
    public ResponseEntity<Plot> updatePlot(@PathVariable Long id, @RequestBody PlotRequest plotRequest) {
        return ResponseEntity.ok().body(plotService.updatePlot(id, plotRequest));
    }

    /**
     * fetch all plots of land
     *
     * @return
     */
    @GetMapping("/plots")
    @ApiOperation(value = "Fetch details of all Plots of land existing so far in Database")
    public List<PlotDto> fetchPlots() {
        log.info("request received to fetch all plot details");
        return plotService.fetchPlots();
    }


    @GetMapping("/mock")
    @ApiOperation(value = "This API used to mock call like coming from scheduler")
    public void mockScheduler() {
        irrigationScheduler.checkIrrigationEveryHour();
    }
}
