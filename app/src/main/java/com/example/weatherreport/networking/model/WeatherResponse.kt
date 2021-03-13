package com.example.weatherreport.networking.model

data class WeatherResponse(
    val cityName: String,
    val date: Long,
    val temperatureMin: Double,
    val temperatureMax: Double,
    val humidity: Int,
    val clouds: Int
)
