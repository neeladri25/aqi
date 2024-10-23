package com.aqi.aqi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AQIExceptionHandler {

    @ExceptionHandler(AirQualityIndexException.class)
    public ResponseEntity<Object> handleAQIException(AirQualityIndexException ex) {
        ErrorResponse errorResponse = ErrorResponse.builder().status("error").message(ex.getMessage()).build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }
}
