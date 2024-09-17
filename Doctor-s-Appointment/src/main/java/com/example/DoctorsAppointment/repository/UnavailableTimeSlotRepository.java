package com.example.DoctorsAppointment.repository;

import com.example.DoctorsAppointment.model.UnavailableTimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnavailableTimeSlotRepository extends JpaRepository<UnavailableTimeSlot, Long> {
}
