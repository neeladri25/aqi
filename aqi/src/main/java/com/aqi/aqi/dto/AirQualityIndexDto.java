package com.aqi.aqi.dto;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class AirQualityIndexDto {

    Integer idx;
    Integer aqi;
    String cityName;

    private List<ForecastDto> o3;
    private List<ForecastDto> pm25;
    private List<ForecastDto> pm10;
    private List<ForecastDto> uvi;
}
