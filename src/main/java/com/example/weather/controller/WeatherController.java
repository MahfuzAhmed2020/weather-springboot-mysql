package com.example.weather.controller;

import com.example.weather.entity.WeatherSearch;
import com.example.weather.model.WeatherResponse;
import com.example.weather.service.WeatherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/weather")
@Tag(name = "Weather", description = "Weatherstack weather operations")
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping
    @Operation(summary = "Get current weather for a city")
    public ResponseEntity<WeatherResponse> getWeather(
            @Parameter(description = "City name, for example Buffalo")
            @RequestParam String city) {

        WeatherResponse response = weatherService.getWeather(city);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/history")
    @Operation(summary = "Get weather lookup history")
    public ResponseEntity<List<WeatherSearch>> getHistory() {

        return ResponseEntity.ok(weatherService.getHistory());
    }

    @GetMapping("/history/{id}")
    @Operation(summary = "Get one weather lookup by ID")
    public ResponseEntity<WeatherSearch> getHistoryById(
            @PathVariable Long id) {

        return ResponseEntity.ok(weatherService.getHistoryById(id));
    }
}
