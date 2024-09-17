package com.example.DoctorsAppointment.repository;

import com.example.DoctorsAppointment.model.AvailableTimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface AvailableTimeSlotRepository extends JpaRepository<AvailableTimeSlot, Long> {
    List<AvailableTimeSlot> findAllByDoctorDoctorIdAndDateAndIsAvailable(Long doctorId, LocalDate date, boolean b);

    boolean existsByDoctorDoctorIdAndDateAndStartTimeAndEndTime(Long doctorId, LocalDate date, LocalTime startTime, LocalTime endTime);
}
