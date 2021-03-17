package com.example.weatherreport.data.weather.remote

import com.example.weatherreport.data.weather.Weather

interface WeatherRemoteDataStore {
    suspend fun get(city: String, units: String, lang: String): Weather?
}