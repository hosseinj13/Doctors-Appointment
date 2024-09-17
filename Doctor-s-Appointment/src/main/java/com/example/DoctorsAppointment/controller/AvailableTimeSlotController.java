package com.example.DoctorsAppointment.controller;

import com.example.DoctorsAppointment.dto.AvailableTimeSlotDTO;
import com.example.DoctorsAppointment.dto.AvailableTimeSlotForDayRequest;
import com.example.DoctorsAppointment.dto.AvailableTimeSlotRequest;
import com.example.DoctorsAppointment.exception.DuplicateTimeSlotException;
import com.example.DoctorsAppointment.exception.TimeSlotAlreadyBookedException;
import com.example.DoctorsAppointment.exception.TimeSlotNotFoundException;
import com.example.DoctorsAppointment.service.AvailableTimeSlotService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/available-time-slots")
public class AvailableTimeSlotController {

    private static final Logger log = LoggerFactory.getLogger(AvailableTimeSlotController.class);
    private final AvailableTimeSlotService timeSlotService;

    @PostMapping
    public ResponseEntity<Void> addAvailableTimeSlots(@RequestBody AvailableTimeSlotRequest request) {
        timeSlotService.addAvailableTimeSlots(request.getDoctorId(), request.getDate(), request.getStartTime(), request.getEndTime());
        return ResponseEntity.ok().build();
    }


    @ExceptionHandler(DuplicateTimeSlotException.class)
    public ResponseEntity<String> handleDuplicateTimeSlot(DuplicateTimeSlotException ex) {
        log.error("Duplicate time slot error: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @GetMapping("/all")
    public ResponseEntity<List<AvailableTimeSlotDTO>> getAvailableAndBookedTimeSlots() {
        List<AvailableTimeSlotDTO> timeSlots = timeSlotService.getAvailableAndBookedTimeSlots();
        return ResponseEntity.ok(timeSlots);
    }

    @PostMapping("/for-day")
    public ResponseEntity<List<AvailableTimeSlotDTO>> getAvailableTimeSlotsForDay(
            @RequestBody AvailableTimeSlotForDayRequest request) {

        List<AvailableTimeSlotDTO> availableTimeSlots = timeSlotService.getAvailableTimeSlotsForDay(
                request.getDoctorId(), request.getDate());
        return ResponseEntity.ok(availableTimeSlots);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTimeSlot(@PathVariable Long id) {
        timeSlotService.deleteTimeSlot(id);
        log.info("Successfully deleted time slot with id: {}", id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(TimeSlotNotFoundException.class)
    public ResponseEntity<String> handleTimeSlotNotFound(TimeSlotNotFoundException ex) {
        log.error("Error deleting time slot: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(TimeSlotAlreadyBookedException.class)
    public ResponseEntity<String> handleTimeSlotAlreadyBooked(TimeSlotAlreadyBookedException ex) {
        log.error("Error deleting time slot: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(ex.getMessage());
    }
}
