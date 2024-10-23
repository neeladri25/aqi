package com.aqi.aqi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AirQualityIndexResponseDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Integer idx;
    private Integer aqi;
    private String cityName;
    private List<ForecastDto> o3;
    private List<ForecastDto> pm25;
    private List<ForecastDto> pm10;
    private List<ForecastDto> uvi;
}
