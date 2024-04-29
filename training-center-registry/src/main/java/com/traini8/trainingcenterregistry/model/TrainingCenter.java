package com.traini8.trainingcenterregistry.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TrainingCenter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer centerId;

    @Size(max = 40, message = "Center name must be less than 40 characters")
    private String centerName;

    @Pattern(regexp = "\\p{Alnum}{12}", message = "Center code must be exactly 12 alphanumeric characters")
    private String centerCode;

    @Embedded
    private Address centerAddress;

    private Integer studentCapacity;

    private List<String> coursesOffered;

    private LocalDateTime createdOn = LocalDateTime.now();
    @Email
    private String contactEmail;

    @Size(min = 10,max = 10, message = "Phone number must be exactly 10 digits long")
    @Pattern(regexp = "\\d+", message = "Phone number must contain only digits")
    private String contactPhone;
}
