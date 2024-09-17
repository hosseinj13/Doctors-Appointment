package com.example.DoctorsAppointment.mapper;

import com.example.DoctorsAppointment.dto.DoctorDTO;
import com.example.DoctorsAppointment.model.Doctor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DoctorMapper {

    DoctorMapper INSTANCE = Mappers.getMapper(DoctorMapper.class);

    Doctor toDoctor(DoctorDTO doctorDTO);

    DoctorDTO toDoctorDTO(Doctor doctor);
}
