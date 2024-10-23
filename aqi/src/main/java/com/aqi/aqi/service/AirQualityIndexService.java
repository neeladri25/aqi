package com.aqi.aqi.service;

import com.aqi.aqi.cache.AirQualityIndexCacheHandler;
import com.aqi.aqi.dto.AirQualityIndexResponseDto;
import com.aqi.aqi.dto.ForecastDto;
import com.aqi.aqi.exception.AirQualityIndexException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class AirQualityIndexService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AirQualityIndexCacheHandler airQualityIndexCacheHandler;

    @Value("${aqi.api.token}")
    private String apiToken;

    private static final String API_URL = "https://api.waqi.info/feed/{city}/?token={token}";

    public AirQualityIndexResponseDto getAirQualityIndexByCity(String city) {
        if (city==null || city.isEmpty() || city.isBlank()) {
            throw new AirQualityIndexException("Mandatory field City missing");
        }
        if (airQualityIndexCacheHandler.getAirQualityIndexResponseFromCache(city) != null) {
            return airQualityIndexCacheHandler.getAirQualityIndexResponseFromCache(city);
        }
        String url = API_URL.replace("{city}", city).replace("{token}", apiToken);
        String response = restTemplate.getForObject(url, String.class);
        return mapResponseToDTO(response, city);
    }

    private AirQualityIndexResponseDto mapResponseToDTO(String jsonResponse, String city) {

        AirQualityIndexResponseDto airQualityIndexResponseDto = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonResponse);

            if (rootNode.path("status").asText().equals("ok")) {
                JsonNode dataNode = rootNode.path("data");
                JsonNode forecastNode = dataNode.path("forecast").path("daily");

                airQualityIndexResponseDto = AirQualityIndexResponseDto.builder()
                        .aqi(dataNode.path("aqi").asInt())
                        .idx(dataNode.path("idx").asInt())
                        .cityName(dataNode.path("city").path("name").asText())
                        .o3(parseForecastArray(forecastNode.path("o3")))
                        .pm10(parseForecastArray(forecastNode.path("pm10")))
                        .pm25(parseForecastArray(forecastNode.path("pm25")))
                        .uvi(parseForecastArray(forecastNode.path("uvi")))
                        .build();
                airQualityIndexCacheHandler.putAirQualityIndexResponseInCache(airQualityIndexResponseDto, city);
            } else if (rootNode.path("status").asText().equals("error")) {
                String errorMessage = rootNode.path("data").asText();
                switch (errorMessage) {
                    case "Over quota":
                        throw new AirQualityIndexException("API usage has exceeded the allowed limit.");
                    case "Invalid key":
                        throw new AirQualityIndexException("The API key provided is invalid.");
                    case "Unknown station":
                        throw new AirQualityIndexException("The city you entered is not recognized.");
                    default:
                        throw new AirQualityIndexException("An unknown error occurred.");
                }
            }
        } catch (Exception e) {
            throw new AirQualityIndexException(e.getMessage());
        }

        return airQualityIndexResponseDto;
    }

    private List<ForecastDto> parseForecastArray(JsonNode forecastArray) {
        List<ForecastDto> forecastList = new ArrayList<>();

        for (JsonNode forecastEntry : forecastArray) {
            ForecastDto forecastDTO = ForecastDto.builder()
                    .day(forecastEntry.path("day").asText())
                    .avg(forecastEntry.path("avg").asInt())
                    .max(forecastEntry.path("max").asInt())
                    .min(forecastEntry.path("min").asInt()).build();
            forecastList.add(forecastDTO);
        }
        return forecastList;
    }
}
