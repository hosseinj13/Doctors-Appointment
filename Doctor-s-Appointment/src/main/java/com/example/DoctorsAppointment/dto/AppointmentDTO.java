package com.example.DoctorsAppointment.dto;

import com.example.DoctorsAppointment.enums.AppointmentStatus;
import com.example.DoctorsAppointment.model.Patient;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AppointmentDTO {
     Long appointmentId;
     LocalDateTime reservedDateTime;
     AppointmentStatus appointmentStatus;
     Long patientId;
     Long timeSlotId;
}
