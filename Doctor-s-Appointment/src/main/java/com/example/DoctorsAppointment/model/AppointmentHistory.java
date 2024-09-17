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

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
@Table(name = "appointment_histories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)

public class AppointmentHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long historyId;


    @NotNull
    LocalDateTime changeDate;

    @NotBlank(message = "Old status cannot be blank")
    @Size(max = 50, message = "Old status cannot exceed 50 characters")
    String oldStatus;

    @NotBlank(message = "New status cannot be blank")
    @Size(max = 50, message = "New status cannot exceed 50 characters")
    String newStatus;

    @NotBlank(message = "Changed by cannot be blank")
    @Size(max = 100, message = "Changed by cannot exceed 100 characters")
    String changedBy;

    @ManyToOne
    @JoinColumn(name = "appointment_id", referencedColumnName = "appointmentId")
    private Appointment appointment;

}
