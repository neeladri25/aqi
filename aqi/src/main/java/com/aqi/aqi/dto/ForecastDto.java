package com.aqi.aqi.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ForecastDto {

    private String day;
    private Integer avg;
    private Integer max;
    private Integer min;

}
