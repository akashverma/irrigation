package com.example.irrigation.repository;

import com.example.irrigation.dtos.TimeSlot;
import com.example.irrigation.entity.Plot;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PlotRepository extends CrudRepository<Plot, Long> {
    Optional<Plot> findByTimeSlot(TimeSlot timeSlot);
}
