package com.example.irrigation.entity;

import com.example.irrigation.dtos.TimeSlot;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Plot plot = (Plot) o;
        return id != null && Objects.equals(id, plot.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
