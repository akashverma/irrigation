package com.example.irrigation.controller;


import com.example.irrigation.IrrigationTestHelper;
import com.example.irrigation.controllers.PlotController;
import com.example.irrigation.dtos.PlotDto;
import com.example.irrigation.dtos.TimeSlot;
import com.example.irrigation.dtos.request.PlotRequest;
import com.example.irrigation.entity.Plot;
import com.example.irrigation.exceptions.PlotServiceException;
import com.example.irrigation.services.PlotService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static com.example.irrigation.IrrigationTestHelper.mockSavedPlot;
import static com.example.irrigation.IrrigationTestHelper.plotRequest;
import static com.example.irrigation.TestConstants.SAVED_PLOT_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PlotControllerTest {

    @InjectMocks
    private PlotController plotController;

    @Mock
    private PlotService plotService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFetchPlots() {
        Mockito.when(plotService.fetchPlots())
                .thenReturn(IrrigationTestHelper.mockPlotDtos());

        List<PlotDto> response = plotController.fetchPlots();

        Assertions.assertNotNull(response);
        assertTrue(response.get(0).isIrrigated());
        assertFalse(response.get(1).isIrrigated());
    }

    @Test
    void testAddPlot() {
        PlotRequest request = plotRequest();
        Mockito.when(plotService.addPlot(request))
                .thenReturn(mockSavedPlot());

        ResponseEntity<Plot> plotResponseEntity = plotController.addPlot(request);
        Assertions.assertNotNull(plotResponseEntity);

        assertEquals(HttpStatus.ACCEPTED, plotResponseEntity.getStatusCode());
        assertEquals(SAVED_PLOT_ID, plotResponseEntity.getBody().getId());
        assertEquals("10", plotResponseEntity.getBody().getLength());
        assertEquals("20", plotResponseEntity.getBody().getBreadth());
        assertEquals("100", plotResponseEntity.getBody().getAmountOfWater());
        assertFalse(plotResponseEntity.getBody().isIrrigated());
        assertEquals(TimeSlot.MORNING, plotResponseEntity.getBody().getTimeSlot());
    }


}
