package com.example.irrigation.services;

import com.example.irrigation.dtos.PlotDto;
import com.example.irrigation.dtos.TimeSlot;
import com.example.irrigation.dtos.request.PlotRequest;
import com.example.irrigation.entity.Plot;
import com.example.irrigation.exceptions.PlotServiceException;
import com.example.irrigation.repository.PlotRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static com.example.irrigation.IrrigationTestHelper.mockPlots;
import static com.example.irrigation.IrrigationTestHelper.mockSavedPlot;
import static com.example.irrigation.IrrigationTestHelper.plotRequest;
import static com.example.irrigation.TestConstants.TEST_AMT_WATER;
import static com.example.irrigation.TestConstants.TEST_BREADTH;
import static com.example.irrigation.TestConstants.TEST_LENGTH;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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

        PlotRequest plotRequest = plotRequest();
        plotRequest.setBreadth("10");
        plotRequest.setLength("20");
        Plot response = plotService.addPlot(plotRequest);

        Assertions.assertEquals(TEST_BREADTH, response.getBreadth());
        Assertions.assertEquals(TEST_LENGTH, response.getLength());
        Assertions.assertEquals(TEST_AMT_WATER, response.getAmountOfWater());
        Mockito.verify(plotRepository, Mockito.times(1)).save(any());
    }

    @Test
    void testFetchAllPlots() {

        Mockito.when(plotRepository.findAll())
                .thenReturn(mockPlots());

        List<PlotDto> response = plotService.fetchPlots();
        assertNotNull(response);
        assertEquals(2, response.size());

    }

    @Test
    void testUpdatePlot() {
        PlotRequest request = plotRequest();

        Mockito.when(plotRepository.findById(any()))
                .thenReturn(Optional.of(mockSavedPlot()));

        Mockito.when(plotRepository.save(any()))
                .thenReturn(mockSavedPlot());

        Plot updatedPlot = plotService.updatePlot(100L, request);
        Assertions.assertNotNull(updatedPlot);
        assertEquals(TEST_AMT_WATER, updatedPlot.getAmountOfWater());
        assertEquals(TimeSlot.MORNING, updatedPlot.getTimeSlot());
        assertEquals(TEST_BREADTH, updatedPlot.getBreadth());
        assertEquals(TEST_LENGTH, updatedPlot.getLength());
    }

    @Test
    void testUpdatePlot_NegativeScenario() {
        PlotRequest request = plotRequest();

        Mockito.when(plotRepository.findById(any()))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(PlotServiceException.class, () -> {
            plotService.updatePlot(100L, request);
        });
    }

}
