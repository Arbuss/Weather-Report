package com.example.weatherreport.data.weather

import com.example.weatherreport.data.weather.remote.networking.model.WeatherResponse

data class Weather(
    val cityName: String,
    val date: Long,
    val temperatureCurrent: Double,
    val temperatureMin: Double,
    val temperatureMax: Double,
    val humidity: Int,
    val clouds: Int
)

fun WeatherResponse.toGlobalModel() = Weather(
    name, dt, main.temp, main.temp_min, main.temp_max, main.humidity, clouds.all
)
