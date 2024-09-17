package com.example.DoctorsAppointment.repository;

import com.example.DoctorsAppointment.enums.AppointmentStatus;
import com.example.DoctorsAppointment.model.Appointment;
import com.example.DoctorsAppointment.model.AvailableTimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    boolean existsByAvailableTimeSlotAndAppointmentStatus(AvailableTimeSlot timeSlot, AppointmentStatus status);
    

}

