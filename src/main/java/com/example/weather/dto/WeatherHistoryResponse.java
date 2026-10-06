package com.example.weather.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class WeatherHistoryResponse {

    private Long id;
    private String city;
    private Integer temperature;
    private Integer feelsLike;
    private String description;
    private LocalDateTime searchedAt;
}
