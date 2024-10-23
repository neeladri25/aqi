package com.aqi.aqi.cache;

import com.aqi.aqi.dto.AirQualityIndexResponseDto;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class AirQualityIndexCacheHandler {

    @CachePut(cacheNames = "aqi_cache", key = "#city")
    public AirQualityIndexResponseDto putAirQualityIndexResponseInCache(AirQualityIndexResponseDto response, String city) {
        return response;
    }

    @Cacheable(cacheNames = "aqi_cache", key = "#city")
    public AirQualityIndexResponseDto getAirQualityIndexResponseFromCache(String city) {
        AirQualityIndexResponseDto airQualityIndexResponseDto = null;
        return airQualityIndexResponseDto;
    }

}
