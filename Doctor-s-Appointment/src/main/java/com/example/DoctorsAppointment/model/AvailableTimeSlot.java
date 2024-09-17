package com.example.DoctorsAppointment.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "available_time_slots")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AvailableTimeSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long timeSlotId;

    @NotNull
    LocalTime startTime;

    @NotNull
    LocalTime endTime;

    @NotNull
    Boolean isAvailable;

    @NotNull
    LocalDate date;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    Doctor doctor;

    @Version
    Integer version;
}
