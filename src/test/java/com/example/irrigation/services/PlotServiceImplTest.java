package com.example.irrigation.services;

import com.example.irrigation.dtos.TimeSlot;
import com.example.irrigation.dtos.request.PlotRequest;
import com.example.irrigation.entity.Plot;
import com.example.irrigation.repository.PlotRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class PlotServiceImplTest {

    @InjectMocks
    private PlotServiceImpl plotService;

    @Mock
    private PlotRepository plotRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void addPlotTest() {
        Mockito.when(plotRepository.save(any()))
                .thenReturn(mockSavedPlot());

        PlotRequest plotRequest = new PlotRequest();
        plotRequest.setBreadth("10");
        plotRequest.setLength("20");
        plotService.addPlot(plotRequest);

        Mockito.verify(plotRepository, Mockito.times(1)).save(any());
    }

    private Plot mockSavedPlot() {
        Plot plot = new Plot();
        plot.setTimeSlot(TimeSlot.MORNING);
        plot.setIrrigated(false);
        plot.setAmountOfWater("100");
        plot.setBreadth("10");
        plot.setLength("20");
        return plot;
    }
}
