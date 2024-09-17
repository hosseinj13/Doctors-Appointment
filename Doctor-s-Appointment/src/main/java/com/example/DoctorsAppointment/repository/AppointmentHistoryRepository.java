package com.example.DoctorsAppointment.repository;

import com.example.DoctorsAppointment.model.AppointmentHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentHistoryRepository extends JpaRepository<AppointmentHistory, Long> {
}

