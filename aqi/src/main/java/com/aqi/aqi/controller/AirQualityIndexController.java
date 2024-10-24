package com.aqi.aqi.controller;

import com.aqi.aqi.dto.AirQualityIndexResponseDto;
import com.aqi.aqi.service.AirQualityIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/air-quality-index/")
public class AirQualityIndexController {

    @Autowired
    AirQualityIndexService airQualityIndexService;

    @GetMapping
    public ResponseEntity<?> getAirQualityIndex(@RequestParam String city) {
        try {
            AirQualityIndexResponseDto airQuality = airQualityIndexService.getAirQualityIndexByCity(city);
            return ResponseEntity.ok(airQuality);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
