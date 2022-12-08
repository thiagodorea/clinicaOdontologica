package com.dh.clinicaodonto.exception.handler;

import com.dh.clinicaodonto.exception.InvalidRegistrationException;
import com.dh.clinicaodonto.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

   @ExceptionHandler({ResourceNotFoundException.class})
   public ResponseEntity<String> errorResourceNotFound(ResourceNotFoundException exception) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
   }

   @ExceptionHandler({InvalidRegistrationException.class})
   public ResponseEntity<String> errorInvalidRegistration(InvalidRegistrationException exception) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
   }
}
