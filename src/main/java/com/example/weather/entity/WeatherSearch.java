package com.example.weather.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "weather_search")
@Getter
@Setter
public class WeatherSearch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String city;

    private Integer temperature;

    private Integer feelsLike;

    private String description;

    @Column(nullable = false)
    private LocalDateTime searchedAt;
}
