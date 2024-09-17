package com.example.DoctorsAppointment.exception;

public class NationalCodeAlreadyExistsException extends RuntimeException {
    public NationalCodeAlreadyExistsException(String message) {
        super(message);
    }
}
