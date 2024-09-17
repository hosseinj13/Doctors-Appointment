package com.example.DoctorsAppointment.service;

import com.example.DoctorsAppointment.dto.PatientDTO;
import com.example.DoctorsAppointment.exception.InvalidNationalCodeException;
import com.example.DoctorsAppointment.exception.NationalCodeAlreadyExistsException;
import com.example.DoctorsAppointment.exception.PatientNotFoundException;
import com.example.DoctorsAppointment.mapper.PatientMapper;
import com.example.DoctorsAppointment.model.Patient;
import com.example.DoctorsAppointment.repository.PatientRepository;
import com.example.DoctorsAppointment.util.validation.NationalCodeValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientService {

    private static final Logger log = LoggerFactory.getLogger(PatientService.class);
    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    @Transactional
    public PatientDTO createPatient(PatientDTO patientDTO) {
        String nationalCode = patientDTO.getNationalCode();

        // National code validation
        if (!NationalCodeValidator.isNationalCodeValid(nationalCode)) {
            throw new InvalidNationalCodeException("National code is not valid");
        }

        // Checking the duplication of the national code
        if (patientRepository.existsByNationalCode(nationalCode)) {
            throw new NationalCodeAlreadyExistsException("National code already exists");
        }

        log.info("Creating new patient with email: {}", patientDTO.getEmail());
        Patient patient = patientMapper.toPatient(patientDTO);
        Patient savedPatient = patientRepository.save(patient);
        return patientMapper.toPatientDTO(savedPatient);
    }


    @Transactional(readOnly = true)
    public PatientDTO getPatient(Long id) {
        log.info("Fetching patient with id: {}", id);
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found"));
        return patientMapper.toPatientDTO(patient);
    }

    @Transactional
    public PatientDTO updatePatient(Long id, PatientDTO patientDTO) {
        log.info("Updating patient with id: {}", id);
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found"));
        patientMapper.updatePatientFromDto(patientDTO, patient);
        Patient updatedPatient = patientRepository.save(patient);
        return patientMapper.toPatientDTO(updatedPatient);
    }

    @Transactional
    public void deletePatient(Long id) {
        log.info("Deleting patient with id: {}", id);
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found"));
        patientRepository.delete(patient);
    }

    @Transactional(readOnly = true)
    public List<PatientDTO> getAllPatients() {
        log.info("Fetching all patients");
        return patientRepository.findAll().stream()
                .map(patientMapper::toPatientDTO)
                .collect(Collectors.toList());
    }
}
