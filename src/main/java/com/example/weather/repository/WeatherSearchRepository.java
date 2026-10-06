package com.example.weather.repository;

import com.example.weather.entity.WeatherSearch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherSearchRepository extends JpaRepository<WeatherSearch, Long> {
}
