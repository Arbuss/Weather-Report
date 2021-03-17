package com.example.weatherreport.data.weather.remote

import com.example.weatherreport.Const
import com.example.weatherreport.data.weather.Weather
import com.example.weatherreport.data.weather.remote.networking.WeatherApi
import com.example.weatherreport.data.weather.toGlobalModel
import javax.inject.Inject

class WeatherRemoteDataStoreImpl @Inject constructor(private val weatherApi: WeatherApi) :
    WeatherRemoteDataStore {

    override suspend fun get(city: String, units: String, lang: String): Weather? {
        val response = weatherApi.getWeather(city, units, lang, Const.Networking.API_KEY).execute()
        return response.body()
            ?.toGlobalModel()
    }
}