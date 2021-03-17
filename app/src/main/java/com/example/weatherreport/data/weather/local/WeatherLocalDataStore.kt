package com.example.weatherreport.data.weather.local

import com.example.weatherreport.data.weather.Weather

interface WeatherLocalDataStore {
    suspend fun add(weather: Weather)

    suspend fun get(): List<Weather>
    suspend fun get(id: Long): Weather
}