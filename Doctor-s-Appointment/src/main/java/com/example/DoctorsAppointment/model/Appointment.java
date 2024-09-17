package com.example.DoctorsAppointment.model;

import com.example.DoctorsAppointment.enums.AppointmentStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long appointmentId;


    @NotNull
    LocalDateTime reservedDateTime;

    @Enumerated(EnumType.STRING)
    AppointmentStatus appointmentStatus;

    @ManyToOne
    @JoinColumn(name = "time_slot_id")
    AvailableTimeSlot availableTimeSlot;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    Patient patient;

    public Appointment(AvailableTimeSlot timeSlot, Patient patient) {
        this.availableTimeSlot = timeSlot;
        this.patient = patient;
    }
}
