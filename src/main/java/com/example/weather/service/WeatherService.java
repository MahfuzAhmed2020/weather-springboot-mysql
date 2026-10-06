package com.example.weather.service;

import com.example.weather.dto.WeatherHistoryResponse;
import com.example.weather.entity.WeatherSearch;
import com.example.weather.model.WeatherResponse;
import com.example.weather.repository.WeatherSearchRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WeatherService {

    private static final String API =
            "http://api.weatherstack.com/current"
                    + "?access_key=API_KEY&query=CITY";

    private final RestTemplate restTemplate;
    private final WeatherSearchRepository repository;
    private final String apiKey;

    public WeatherService(
            RestTemplate restTemplate,
            WeatherSearchRepository repository,
            @Value("${weather.api.key}") String apiKey) {

        this.restTemplate = restTemplate;
        this.repository = repository;
        this.apiKey = apiKey;
    }

    public WeatherResponse getWeather(String city) {

        String finalApi = API
                .replace("API_KEY", apiKey)
                .replace("CITY", city);

        ResponseEntity<WeatherResponse> response =
                restTemplate.exchange(
                        finalApi,
                        HttpMethod.GET,
                        null,
                        WeatherResponse.class
                );

        WeatherResponse body = response.getBody();

        if (body != null && body.getCurrent() != null) {
            saveSearch(city, body);
        }

        return body;
    }

    private void saveSearch(String city, WeatherResponse response) {

        WeatherSearch search = new WeatherSearch();

        search.setCity(city);
        search.setTemperature(response.getCurrent().getTemperature());
        search.setFeelsLike(response.getCurrent().getFeelslike());

        String description = null;

        if (response.getCurrent().getWeatherDescriptions() != null
                && !response.getCurrent().getWeatherDescriptions().isEmpty()) {
            description = response.getCurrent()
                    .getWeatherDescriptions()
                    .get(0);
        }

        search.setDescription(description);
        search.setSearchedAt(LocalDateTime.now());

        repository.save(search);
    }

    public List<WeatherSearch> getHistory() {
        return repository.findAll();
    }

    public WeatherSearch getHistoryById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Weather history not found: " + id));
    }
}
