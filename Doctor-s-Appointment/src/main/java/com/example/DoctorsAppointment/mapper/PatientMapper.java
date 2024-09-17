package com.example.DoctorsAppointment.mapper;

import com.example.DoctorsAppointment.dto.PatientDTO;
import com.example.DoctorsAppointment.model.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PatientMapper {
    PatientMapper INSTANCE = Mappers.getMapper(PatientMapper.class);

    PatientDTO toPatientDTO(Patient patient);
    Patient toPatient(PatientDTO patientDTO);

    void updatePatientFromDto(PatientDTO patientDto, @MappingTarget Patient patient);
}

