package com.example.weatherreport.data.weather.remote.networking

import com.example.weatherreport.data.weather.remote.networking.model.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherApi {
    @GET("weather?")
    fun getWeather(
        @Query("q") cityName: String,
        @Query("units") units: String,
        @Query("lang") lang: String,
        @Query("appid") appId: String,
    ): Call<WeatherResponse>
}