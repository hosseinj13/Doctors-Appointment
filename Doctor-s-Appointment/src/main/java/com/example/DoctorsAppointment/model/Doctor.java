package com.example.DoctorsAppointment.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "doctors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long doctorId;

    String firstName;
    String lastName;
    String specialty;
    String phoneNumber;
    String email;

    public Doctor(Long id) {
        this.doctorId = id;
    }
}
