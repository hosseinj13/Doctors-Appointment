package com.example.DoctorsAppointment.service;

import com.example.DoctorsAppointment.dto.AppointmentDTO;
import com.example.DoctorsAppointment.enums.AppointmentStatus;
import com.example.DoctorsAppointment.exception.PatientNotFoundException;
import com.example.DoctorsAppointment.exception.TimeSlotAlreadyBookedException;
import com.example.DoctorsAppointment.exception.TimeSlotNotFoundException;
import com.example.DoctorsAppointment.mapper.AppointmentMapper;
import com.example.DoctorsAppointment.model.Appointment;
import com.example.DoctorsAppointment.model.AvailableTimeSlot;
import com.example.DoctorsAppointment.model.Patient;
import com.example.DoctorsAppointment.repository.AppointmentRepository;
import com.example.DoctorsAppointment.repository.AvailableTimeSlotRepository;
import com.example.DoctorsAppointment.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Service
@RequiredArgsConstructor
@Slf4j

public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final AvailableTimeSlotRepository availableTimeSlotRepository;
    private final PatientRepository patientRepository;


    @Transactional
    public AppointmentDTO reserveTimeSlot(Long timeSlotId, Long patientId) {
        log.info("Reserving time slot with id: {} for patient with id: {}", timeSlotId, patientId);

        AvailableTimeSlot timeSlot = availableTimeSlotRepository.findById(timeSlotId)
                .orElseThrow(() -> new TimeSlotNotFoundException("Time slot not found"));

        if (!timeSlot.getIsAvailable()) {
            log.warn("Time slot with id: {} is already booked.", timeSlotId);
            throw new TimeSlotAlreadyBookedException("This time slot has already been booked.");
        }

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found"));

        Appointment appointment = new Appointment(timeSlot, patient);
        appointment.setReservedDateTime(LocalDateTime.now());
        appointment.setAppointmentStatus(AppointmentStatus.SCHEDULED);

        timeSlot.setIsAvailable(false);
        availableTimeSlotRepository.save(timeSlot);

        Appointment savedAppointment = appointmentRepository.save(appointment);

        log.info("Successfully reserved time slot with id: {} for patient with id: {}", timeSlotId, patientId);

        return AppointmentMapper.INSTANCE.toAppointmentDTO(savedAppointment);
    }
}
