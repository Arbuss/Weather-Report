package com.example.weatherreport.data.weather

import com.example.weatherreport.data.database.entity.WeatherEntity
import com.example.weatherreport.data.weather.remote.networking.model.WeatherResponse
import java.io.Serializable

data class Weather(
    val id: Long,
    val cityName: String,
    val date: Long,
    val temperature: Double,
    val humidity: Int,
    val clouds: Int
): Serializable

fun WeatherResponse.toGlobalModel() = Weather(
    id, name, dt * 1000, main.temp, main.humidity, clouds.all
)

fun WeatherEntity.toGlobalModel() = Weather(
    id, cityName, date, temperature, humidity, clouds
)

fun Weather.toEntity() = WeatherEntity(
    id, cityName, date, temperature, humidity, clouds
)