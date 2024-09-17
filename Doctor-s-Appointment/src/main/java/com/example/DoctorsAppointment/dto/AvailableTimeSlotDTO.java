package com.example.DoctorsAppointment.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)

public class AvailableTimeSlotDTO {


     @NotNull(message = "Start time is required")
     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
     LocalTime startTime;

     @NotNull(message = "End time is required")
     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
     LocalTime endTime;

     @NotNull(message = "Availability status is required")
     Boolean isAvailable;

     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
     LocalDate date;

     Long doctorId;

     Integer version;
}
