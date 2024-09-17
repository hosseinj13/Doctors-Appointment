package com.example.DoctorsAppointment.repository;

import com.example.DoctorsAppointment.model.PastAppointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PastAppointmentRepository extends JpaRepository<PastAppointment, Long> {
}

