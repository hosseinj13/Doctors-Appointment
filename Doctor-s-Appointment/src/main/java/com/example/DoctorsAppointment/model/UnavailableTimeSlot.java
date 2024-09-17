package com.example.DoctorsAppointment.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "unavailable_time_slots")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)

public class UnavailableTimeSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long unavailableTimeSlotId;


    @NotNull
    LocalDateTime startTime;

    @NotNull
    LocalDateTime endTime;

    @ManyToOne
    @JoinColumn(name = "doctor_id", insertable = false, updatable = false)
    Doctor doctor;
}
