package com.aqi.aqi.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
public class ForecastDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String day;
    private Integer avg;
    private Integer max;
    private Integer min;

}
