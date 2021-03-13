package com.example.weatherreport.networking

import com.example.weatherreport.networking.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherApi {
    @GET("/weather/{q}{appid}{units}{lang}")
    fun getWeather(
        @Path("q") cityName: String,
        @Path("units") units: String,
        @Path("lang") lang: String,
        @Path("appid") appId: String,
    ): WeatherResponse
}