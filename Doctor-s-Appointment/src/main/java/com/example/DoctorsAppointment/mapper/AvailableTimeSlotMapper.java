package com.example.DoctorsAppointment.mapper;

import com.example.DoctorsAppointment.dto.AvailableTimeSlotDTO;
import com.example.DoctorsAppointment.model.Appointment;
import com.example.DoctorsAppointment.model.AvailableTimeSlot;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AvailableTimeSlotMapper {
    AvailableTimeSlotMapper INSTANCE = Mappers.getMapper(AvailableTimeSlotMapper.class);

    @Mapping(source = "doctor.doctorId", target = "doctorId")
    AvailableTimeSlotDTO toAvailableTimeSlotDTO(AvailableTimeSlot availableTimeSlot);

    @Mapping(source = "doctorId", target = "doctor.doctorId")
    AvailableTimeSlot toAvailableTimeSlot(AvailableTimeSlotDTO availableTimeSlotDTO);

}
