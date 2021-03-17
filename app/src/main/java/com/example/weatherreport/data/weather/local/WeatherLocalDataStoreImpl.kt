package com.example.weatherreport.data.weather.local

import com.example.weatherreport.data.database.dao.WeatherDao
import com.example.weatherreport.data.weather.Weather
import com.example.weatherreport.data.weather.toEntity
import com.example.weatherreport.data.weather.toGlobalModel
import javax.inject.Inject

class WeatherLocalDataStoreImpl @Inject constructor(private val weatherDao: WeatherDao) :
    WeatherLocalDataStore {

    override suspend fun add(weather: Weather) {
        weatherDao.insert(weather.toEntity())
    }

    override suspend fun get(): List<Weather> {
        return weatherDao.get().map { it.toGlobalModel() }
    }

    override suspend fun get(id: Long): Weather {
        return weatherDao.get(id).toGlobalModel()
    }
}