package com.example.irrigation.scheduler;

import com.example.irrigation.dtos.TimeSlot;
import com.example.irrigation.entity.Plot;
import com.example.irrigation.repository.PlotRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class IrrigationScheduler {

    /**
     * default slot picker. Irrigation Starts with MORNING slot by default
     */
    @Value(value = "${slot.picker}")
    private String slotPicker;

    @Autowired
    private PlotRepository plotRepository;

    /**
     * executing a task on every three hours to check for schedule
     */
    @Scheduled(fixedRate = 10800000)
    public void scheduledJob() {

        log.info("IrrigationScheduler Fired!");
        TimeSlot currentSlot = deduceSlot();

        Optional<Plot> optionalPlot = plotRepository.findByTimeSlot(currentSlot);

        if (optionalPlot.isPresent()) {
            Plot plot = optionalPlot.get();
            if (!plot.isIrrigated()) {
                log.info("Irrigated Plot with id: {}", plot.getId());
                plot.setIrrigated(true);
                plotRepository.save(plot);
                updateNextSlot(currentSlot);
            }
        } else {
            log.error("No Scheduled plot found for irrigation. Check Seed Data!!");
            log.error("Send email/SMS notification to Admin!");
        }
    }

    /**
     * helper method to decide current slot awaited for irrigation
     *
     * @return TimeSlot
     */
    TimeSlot deduceSlot() {
        TimeSlot currentSlot;
        if (slotPicker.equals(TimeSlot.MORNING.toString())) {
            currentSlot = TimeSlot.MORNING;
        } else if (slotPicker.equals(TimeSlot.NOON.toString())) {
            currentSlot = TimeSlot.NOON;
        } else {
            currentSlot = TimeSlot.EVENING;
        }
        return currentSlot;
    }

    /**
     * helper method to update the next slot due for irrigation after completion of current slot
     *
     * @param currentSlot
     */
    void updateNextSlot(TimeSlot currentSlot) {
        if (currentSlot.equals(TimeSlot.MORNING)) {
            slotPicker = TimeSlot.NOON.toString();
        }

        if (currentSlot.equals(TimeSlot.NOON)) {
            slotPicker = TimeSlot.EVENING.toString();
        }

        if (currentSlot.equals(TimeSlot.EVENING)) {
            slotPicker = TimeSlot.MORNING.toString();
        }
        log.info("Scheduled Job Completed! Next Slot due for Irrigation is: {}", slotPicker);
    }
}
