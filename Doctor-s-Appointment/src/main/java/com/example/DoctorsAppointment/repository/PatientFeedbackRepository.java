package com.example.DoctorsAppointment.repository;

import com.example.DoctorsAppointment.model.PatientFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientFeedbackRepository extends JpaRepository<PatientFeedback, Long> {

}

