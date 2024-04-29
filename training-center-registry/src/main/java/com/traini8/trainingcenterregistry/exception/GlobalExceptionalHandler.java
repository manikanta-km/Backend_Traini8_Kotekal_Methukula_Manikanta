package com.traini8.trainingcenterregistry.exception;

import com.traini8.trainingcenterregistry.model.ErrorResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionalHandler {

    // Handle MethodArgumentNotValidException
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        // Extract field errors from the exception
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        // Construct error message from field errors
        String errorMessage = fieldErrors.stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining("; "));
        // Create ErrorResponse object
        ErrorResponse errorResponse = new ErrorResponse(errorMessage,false);
        // Return error response with BAD_REQUEST status
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }

    // Handle IllegalStateException
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity <ErrorResponse> handleIllegalStateException(IllegalStateException ex){
        // Create ErrorResponse object with message from the exception
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), false);
        // Return error response with INTERNAL_SERVER_ERROR status
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Handle DateTimeParseException
    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<String> handleDateTimeParseException(DateTimeParseException ex) {
        // Define error message for DateTimeParseException
        String errorMessage = "Date and time should be in a valid format (yyyy-MM-dd'T'HH:mm:ss).";
        // Return error message with BAD_REQUEST status
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    // Handle ConstraintViolationException
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException ex) {
        // Define error message for ConstraintViolationException
        String errorMessage = "Please check your input ";
        // Return error message with BAD_REQUEST status
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    // Handle SQLIntegrityConstraintViolationException
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException ex) {
        // Define error message for SQLIntegrityConstraintViolationException
        String errorMessage = "You have entered a center code that already exists. Please provide a unique center code";
        // Create ErrorResponse object
        ErrorResponse errorResponse = new ErrorResponse(errorMessage, false);
        // Return error response with BAD_REQUEST status
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
