package com.traini8.trainingcenterregistry.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = "centerCode", name = "unique_center_code_constraint")
})
public class TrainingCenter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)   // Auto-generated ID strategy
    private Integer centerId;
    @Size(max = 40, message = "Center name must be less than 40 characters")
    private String centerName;
    @Pattern(regexp = "\\p{Alnum}{12}", message = "Center code must be exactly 12 alphanumeric characters")
    @Column(unique = true)  // To make sure center code is unique
    private String centerCode;
    @Embedded   // Embedding Address object
    @Valid      // Validation of embedded Address object
    private Address centerAddress;
    @Positive(message = "Student capacity must be a positive number")
    private Integer studentCapacity;
    @NotEmpty(message = "Please provide at least one course offered")
    private List<String> coursesOffered;
    private LocalDateTime createdOn = LocalDateTime.now();  // Timestamp for creation
    @Email(message = "Please provide a valid email address")
    private String contactEmail;
    @Size(min = 10,max = 10, message = "Phone number must be exactly 10 digits long")
    @Pattern(regexp = "\\d+", message = "Phone number must contain only digits")
    private String contactPhone;
}
