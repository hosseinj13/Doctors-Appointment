package com.example.DoctorsAppointment.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AvailableTimeSlotForDayRequest {
    Long doctorId;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate date;
}
