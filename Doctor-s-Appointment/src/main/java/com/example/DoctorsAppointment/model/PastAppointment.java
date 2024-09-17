package com.example.DoctorsAppointment.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

import jakarta.validation.constraints.Size;

@Entity
@Table(name = "past_appointments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)

public class PastAppointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long pastAppointmentId;

    @OneToOne
    @JoinColumn(name = "appointment_id", nullable = false)
    Appointment appointment;

    @Size(max = 255, message = "Cancellation reason cannot exceed 255 characters")
    String cancellationReason;
}
