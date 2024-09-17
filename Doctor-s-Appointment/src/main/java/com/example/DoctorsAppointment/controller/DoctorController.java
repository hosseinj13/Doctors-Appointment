package com.example.DoctorsAppointment.controller;

import com.example.DoctorsAppointment.dto.DoctorDTO;
import com.example.DoctorsAppointment.service.DoctorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/doctors")
@RequiredArgsConstructor
@Slf4j

public class DoctorController {

    private final DoctorService doctorService;

    @PostMapping
    public ResponseEntity<DoctorDTO> createDoctor(@Valid @RequestBody DoctorDTO doctorDTO) {
        DoctorDTO createdDoctor = doctorService.createDoctor(doctorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDoctor);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorDTO> getDoctor(@PathVariable Long id) {
        DoctorDTO doctorDTO = doctorService.getDoctor(id);
        return ResponseEntity.ok(doctorDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoctorDTO> updateDoctor(@PathVariable Long id, @Valid @RequestBody DoctorDTO doctorDTO) {
        DoctorDTO updatedDoctor = doctorService.updateDoctor(id, doctorDTO);
        return ResponseEntity.ok(updatedDoctor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<DoctorDTO>> getAllDoctors() {
        List<DoctorDTO> doctors = doctorService.getAllDoctors();
        return ResponseEntity.ok(doctors);
    }
}
