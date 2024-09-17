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
@Table(name = "notifications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long notificationId;

    @NotNull
    Long recipientId;

    @NotBlank(message = "Message cannot be blank")
    @Size(max = 1000, message = "Message cannot exceed 1000 characters")
    String message;

    @NotNull
    LocalDateTime sentDate;

    @NotBlank(message = "Recipient type cannot be blank")
    @Size(max = 50, message = "Recipient type cannot exceed 50 characters")
    String recipientType;

    @ManyToOne
    @JoinColumn(name = "doctor_id", insertable = false, updatable = false)
    Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id", insertable = false, updatable = false)
    Patient patient;
}
