package com.example.irrigation.entity;

import com.example.irrigation.dtos.TimeSlot;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "plot")
public class Plot {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String length;
    private String breadth;

    @Column(name = "water_amount")
    private String amountOfWater;

    @Column(name = "is_irrigated")
    private boolean isIrrigated;

    @Enumerated(EnumType.STRING)
    @Column(name = "time_slot")
    private TimeSlot timeSlot;


}
