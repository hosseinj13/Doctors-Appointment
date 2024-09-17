package com.example.DoctorsAppointment.service;

import com.example.DoctorsAppointment.dto.AvailableTimeSlotDTO;
import com.example.DoctorsAppointment.enums.AppointmentStatus;
import com.example.DoctorsAppointment.exception.DuplicateTimeSlotException;
import com.example.DoctorsAppointment.exception.InvalidTimeSlotException;
import com.example.DoctorsAppointment.exception.TimeSlotAlreadyBookedException;
import com.example.DoctorsAppointment.exception.TimeSlotNotFoundException;
import com.example.DoctorsAppointment.mapper.AvailableTimeSlotMapper;
import com.example.DoctorsAppointment.model.Appointment;
import com.example.DoctorsAppointment.model.AvailableTimeSlot;
import com.example.DoctorsAppointment.model.Doctor;
import com.example.DoctorsAppointment.repository.AppointmentRepository;
import com.example.DoctorsAppointment.repository.AvailableTimeSlotRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AvailableTimeSlotService {

    private final AvailableTimeSlotRepository availableTimeSlotRepository;
    private final AppointmentRepository appointmentRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addAvailableTimeSlots(Long doctorId, LocalDate date, LocalTime startTime, LocalTime endTime) {
        if (startTime.isAfter(endTime)) {
            throw new InvalidTimeSlotException("End time cannot be before start time");
        }

        // Check to avoid adding duplicate appointments for a doctor at the same date and time
        boolean timeSlotExists = availableTimeSlotRepository.existsByDoctorDoctorIdAndDateAndStartTimeAndEndTime(doctorId, date, startTime, endTime);
        if (timeSlotExists) {
            throw new DuplicateTimeSlotException("Time slot already exists for the doctor at the specified time.");
        }

        Duration duration = Duration.between(startTime.atDate(date), endTime.atDate(date));
        if (duration.toMinutes() < 30) {
            log.warn("The time slot is less than 30 minutes and will be ignored.");
            return;
        }

        LocalTime current = startTime;
        while (current.plusMinutes(30).isBefore(endTime) || current.plusMinutes(30).equals(endTime)) {
            AvailableTimeSlot timeSlot = new AvailableTimeSlot();
            timeSlot.setStartTime(current);
            timeSlot.setEndTime(current.plusMinutes(30));
            timeSlot.setIsAvailable(true);
            timeSlot.setDoctor(new Doctor(doctorId));
            timeSlot.setDate(date);
            availableTimeSlotRepository.save(timeSlot);
            current = current.plusMinutes(30);
        }
    }


    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<AvailableTimeSlotDTO> getAvailableAndBookedTimeSlots() {
        List<AvailableTimeSlot> availableTimeSlots = availableTimeSlotRepository.findAll();

        return availableTimeSlots.stream()
                .map(timeSlot -> {
                    // Check if the time slot is booked
                    boolean isBooked = appointmentRepository.existsByAvailableTimeSlotAndAppointmentStatus(timeSlot, AppointmentStatus.SCHEDULED);
                    return AvailableTimeSlotMapper.INSTANCE.toAvailableTimeSlotDTO(timeSlot);
                })
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<AvailableTimeSlotDTO> getAvailableTimeSlotsForDay(Long doctorId, LocalDate date) {
        log.info("Fetching available time slots for doctor with id: {} on date: {}", doctorId, date);

        List<AvailableTimeSlot> availableTimeSlots = availableTimeSlotRepository
                .findAllByDoctorDoctorIdAndDateAndIsAvailable(doctorId, date, true);

        return availableTimeSlots.stream()
                .map(AvailableTimeSlotMapper.INSTANCE::toAvailableTimeSlotDTO)
                .collect(Collectors.toList());
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteTimeSlot(Long timeSlotId) {
        log.info("Attempting to delete time slot with id: {}", timeSlotId);

        AvailableTimeSlot timeSlot = availableTimeSlotRepository.findById(timeSlotId)
                .orElseThrow(() -> new TimeSlotNotFoundException("Time slot not found"));

        if (!timeSlot.getIsAvailable()) {
            log.warn("Time slot with id: {} is already booked and cannot be deleted.", timeSlotId);
            throw new TimeSlotAlreadyBookedException("The time slot has already been booked and cannot be deleted.");
        }
        availableTimeSlotRepository.delete(timeSlot);
        log.info("Successfully deleted time slot with id: {}", timeSlotId);
    }
}
