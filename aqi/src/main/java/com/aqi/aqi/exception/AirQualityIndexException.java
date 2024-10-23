package com.aqi.aqi.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AirQualityIndexException extends RuntimeException{

    private String message;

}
