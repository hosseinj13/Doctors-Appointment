package com.example.DoctorsAppointment.config;

import com.example.DoctorsAppointment.mapper.AppointmentMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public AppointmentMapper appointmentMapper() {
        return Mappers.getMapper(AppointmentMapper.class);
    }

}
