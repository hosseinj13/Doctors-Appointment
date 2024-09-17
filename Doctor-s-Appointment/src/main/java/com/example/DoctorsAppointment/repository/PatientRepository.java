package com.example.DoctorsAppointment.repository;

import com.example.DoctorsAppointment.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    boolean existsByNationalCode(String nationalCode);
}
