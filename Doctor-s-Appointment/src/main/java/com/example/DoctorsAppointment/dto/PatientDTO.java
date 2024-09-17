package com.example.DoctorsAppointment.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PatientDTO {

    @NotBlank(message = "First name is mandatory")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "First name must contain only letters")
    String firstName;

    @NotBlank(message = "Last name is mandatory")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Last name must contain only letters")
    String lastName;

    @NotBlank(message = "Phone number is mandatory")
    @Pattern(regexp = "^(\\+98|98)?(9[0-9]{9})$", message = "Phone number must be a valid Iranian number starting with '9' and may include the country code '+98' or '98'")
    String phoneNumber;


    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    @Size(max = 100, message = "Email cannot exceed 100 characters")
    String email;

    @NotBlank(message = "National code is mandatory")
    @Pattern(regexp = "^[0-9]{10}$", message = "National code must be exactly 10 digits")
    String nationalCode;
}
