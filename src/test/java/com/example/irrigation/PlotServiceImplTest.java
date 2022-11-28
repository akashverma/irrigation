package com.example.irrigation;

import com.example.irrigation.dtos.request.PlotRequest;
import com.example.irrigation.services.PlotServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PlotServiceImplTest {

    @Autowired
    private PlotServiceImpl plotService;

    @Test
    void addPlotTest(){
        PlotRequest plotRequest = new PlotRequest();
        plotRequest.setBreadth("10");
        plotRequest.setLength("20");
        plotService.addPlot(plotRequest);

    }
}
