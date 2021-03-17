package com.example.weatherreport.di.modules

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.example.weatherreport.data.user.UsersDataStore
import com.example.weatherreport.data.user.UsersDataStoreImpl
import com.example.weatherreport.data.weather.remote.WeatherRemoteDataStore
import com.example.weatherreport.data.weather.remote.WeatherRemoteDataStoreImpl
import com.example.weatherreport.data.weather.remote.networking.WeatherApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataStoreModule(private val context: Context) {
    @Provides
    @Singleton
    fun provideSharedPreferences() = PreferenceManager.getDefaultSharedPreferences(context)

    @Provides
    @Singleton
    fun provideUsersDataStore(sharedPreferences: SharedPreferences): UsersDataStore = UsersDataStoreImpl(sharedPreferences)

    @Provides
    @Singleton
    fun provideWeatherRemoteDataStore(weatherApi: WeatherApi): WeatherRemoteDataStore = WeatherRemoteDataStoreImpl(weatherApi)
}