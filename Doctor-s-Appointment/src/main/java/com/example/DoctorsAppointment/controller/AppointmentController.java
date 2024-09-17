package com.example.DoctorsAppointment.controller;

import com.example.DoctorsAppointment.dto.AppointmentDTO;
import com.example.DoctorsAppointment.dto.ReserveTimeSlotRequest;
import com.example.DoctorsAppointment.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/appointments")

public class AppointmentController {

    private final AppointmentService appointmentService;


    @PostMapping("/reserve")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AppointmentDTO> reserveTimeSlot(@RequestBody ReserveTimeSlotRequest request) {
        AppointmentDTO appointmentDTO = appointmentService.reserveTimeSlot(request.getTimeSlotId(), request.getPatientId());
        return ResponseEntity.status(HttpStatus.CREATED).body(appointmentDTO);
    }
}
