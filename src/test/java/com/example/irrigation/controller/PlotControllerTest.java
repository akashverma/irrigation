package com.example.irrigation.controller;


import com.example.irrigation.IrrigationTestHelper;
import com.example.irrigation.controllers.PlotController;
import com.example.irrigation.dtos.PlotDto;
import com.example.irrigation.services.PlotService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

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
                .thenReturn(IrrigationTestHelper.mockedPlots());

        List<PlotDto> response = plotController.fetchPlots();

        Assertions.assertNotNull(response);
        assertTrue(response.get(0).isIrrigated());
        assertFalse(response.get(1).isIrrigated());
    }



}
