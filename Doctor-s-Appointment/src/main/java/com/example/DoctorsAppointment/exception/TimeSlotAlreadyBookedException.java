package com.example.DoctorsAppointment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class TimeSlotAlreadyBookedException extends RuntimeException {
    public TimeSlotAlreadyBookedException(String message) {
        super(message);
    }
}