package com.traini8.trainingcenterregistry.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @NotBlank(message = "Detailed address is required cannot be left blank")
    private String detailedAddress;  // Detailed street address
    @NotBlank(message = "City name is required cannot be left blank")
    private String city;  // City name
    @NotBlank(message = "State name is required cannot be left blank")
    private String state;  // State name
    @NotBlank(message = "PinCode is required cannot be left blank")
    private String pinCode; // Postal code

}
