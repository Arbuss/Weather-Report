package com.example.weatherreport.data.weather

import com.example.weatherreport.data.weather.remote.networking.model.WeatherResponse
import java.io.Serializable

data class Weather(
    val cityName: String,
    val date: Long,
    val temperature: Double,
    val humidity: Int,
    val clouds: Int
): Serializable

fun WeatherResponse.toGlobalModel() = Weather(
    name, dt * 1000, main.temp, main.humidity, clouds.all
)
