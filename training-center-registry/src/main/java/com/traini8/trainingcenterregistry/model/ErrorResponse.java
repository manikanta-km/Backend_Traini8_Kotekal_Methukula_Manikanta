package com.traini8.trainingcenterregistry.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ErrorResponse {
    private String message;  // Message describing the error
    private boolean status;  // Status indicating success or failure  true - success, false - failure
}
