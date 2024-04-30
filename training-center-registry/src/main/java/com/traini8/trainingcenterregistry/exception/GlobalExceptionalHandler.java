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
import org.springframework.web.method.HandlerMethod;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.NoSuchElementException;
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
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException ex, HandlerMethod handlerMethod) {
        // Get the name of the method where the exception occurred
        String methodName = handlerMethod.getMethod().getName();

        // Based on the method name, customize the error message
        String errorMessage;
        switch (methodName) {
            case "updateCourses":
                errorMessage = "Invalid input: Please check the provided courses";
                break;
            case "updateEmail":
                errorMessage = "Invalid input: Please provide a valid email address";
                break;
            case "updateContactPhone":
                errorMessage = "Invalid input: Please provide a valid phone number";
                break;
            case "updateAddress":
                errorMessage = "Invalid input: Please provide a valid address";
                break;
            default:
                errorMessage = "Invalid input";
        }

        // Create ErrorResponse object with the customized error message
        ErrorResponse errorResponse = new ErrorResponse(errorMessage, false);
        // Return error response with BAD_REQUEST status
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
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

    // Handle NoSuchElementException
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorResponse> handleNoSuchElementException(NoSuchElementException ex, HandlerMethod handlerMethod) {
        // Get the name of the method where the exception occurred
        String methodName = handlerMethod.getMethod().getName();

        // Based on the method name, customize the error message
        String errorMessage;
        switch (methodName) {
            case "updateCourses":
                errorMessage = "Unable to update courses: No such training center found";
                break;
            case "updateEmail":
                errorMessage = "Unable to update email: No such training center found";
                break;
            case "updateContactPhone":
                errorMessage = "Unable to update phone number: No such training center found";
                break;
            case "updateAddress":
                errorMessage = "Unable to update address: No such training center found";
                break;
            default:
                errorMessage = "No such training center found";
        }

        // Create ErrorResponse object with the customized error message
        ErrorResponse errorResponse = new ErrorResponse(errorMessage, false);
        // Return error response with NOT_FOUND status
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

}
