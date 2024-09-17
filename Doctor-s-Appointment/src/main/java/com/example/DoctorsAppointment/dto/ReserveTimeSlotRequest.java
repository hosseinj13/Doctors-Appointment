package com.example.DoctorsAppointment.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)

public class ReserveTimeSlotRequest {
     Long timeSlotId;
     Long patientId;
}
