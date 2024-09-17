package com.example.DoctorsAppointment.mapper;

import com.example.DoctorsAppointment.dto.AppointmentDTO;
import com.example.DoctorsAppointment.model.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AppointmentMapper {

    AppointmentMapper INSTANCE = Mappers.getMapper(AppointmentMapper.class);

    AppointmentDTO toAppointmentDTO(Appointment appointment);

    Appointment toAppointment(AppointmentDTO appointmentDTO);
}
