package com.example.DoctorsAppointment.exception;

public class DuplicateTimeSlotException extends RuntimeException {
    public DuplicateTimeSlotException(String message) {
        super(message);
    }
}

