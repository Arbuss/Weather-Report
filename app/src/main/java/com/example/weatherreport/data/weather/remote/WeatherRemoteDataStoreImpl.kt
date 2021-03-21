package com.example.weatherreport.data.weather.remote

import com.example.weatherreport.Const
import com.example.weatherreport.data.weather.Weather
import com.example.weatherreport.data.weather.remote.networking.WeatherApi
import com.example.weatherreport.data.weather.toGlobalModel
import java.net.UnknownHostException
import javax.inject.Inject

class WeatherRemoteDataStoreImpl @Inject constructor(private val weatherApi: WeatherApi) :
    WeatherRemoteDataStore {

    override suspend fun get(city: String, units: String, lang: String): Weather? {
        return try {
            val response =
                weatherApi.getWeather(city, units, lang, Const.Networking.API_KEY).execute()
            response.body()?.toGlobalModel()
        } catch (exception: UnknownHostException) {
            null
        }
    }
}