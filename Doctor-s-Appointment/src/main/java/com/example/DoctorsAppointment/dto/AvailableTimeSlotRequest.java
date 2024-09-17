package com.example.DoctorsAppointment.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AvailableTimeSlotRequest {
    Long doctorId;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    LocalTime startTime;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    LocalTime endTime;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate date;
}
