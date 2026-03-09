package com.training.microservices.customer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.training.microservices.customer.model.ErrorResponse;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Customer Not Found
    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(
            CustomerNotFoundException ex) {

        ErrorResponse error = new ErrorResponse(
                ex.getMessage(),
                404,
                LocalDateTime.now()
        );

        return new ResponseEntity<>(
                error, HttpStatus.NOT_FOUND
        );
    }

    // Customer Not Found
    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(
            AccountNotFoundException ex) {

        ErrorResponse error = new ErrorResponse(
                ex.getMessage(),
                404,
                LocalDateTime.now()
        );

        return new ResponseEntity<>(
                error, HttpStatus.NOT_FOUND
        );
    }

    // Duplicate Customer
    @ExceptionHandler(DuplicateCustomerException.class)
    public ResponseEntity<ErrorResponse> handleDuplicate(
            DuplicateCustomerException ex) {

        ErrorResponse error = new ErrorResponse(
                ex.getMessage(),
                400,
                LocalDateTime.now()
        );

        return new ResponseEntity<>(
                error, HttpStatus.BAD_REQUEST
        );
    }

    // Generic Exception (Fallback)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAll(
            Exception ex) {

        ErrorResponse error = new ErrorResponse(
                "Internal Server Error",
                500,
                LocalDateTime.now()
        );

        return new ResponseEntity<>(
                error, HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}