package com.example.weatherreport.di.modules

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import androidx.room.Room
import com.example.weatherreport.Const
import com.example.weatherreport.data.database.Database
import com.example.weatherreport.data.database.dao.WeatherDao
import com.example.weatherreport.data.user.UsersDataStore
import com.example.weatherreport.data.user.UsersDataStoreImpl
import com.example.weatherreport.data.weather.local.WeatherLocalDataStore
import com.example.weatherreport.data.weather.local.WeatherLocalDataStoreImpl
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
    fun provideUsersDataStore(sharedPreferences: SharedPreferences): UsersDataStore =
        UsersDataStoreImpl(sharedPreferences)

    @Provides
    @Singleton
    fun provideWeatherRemoteDataStore(weatherApi: WeatherApi): WeatherRemoteDataStore =
        WeatherRemoteDataStoreImpl(weatherApi)

    @Provides
    @Singleton
    fun provideDatabase() = Room
        .databaseBuilder(context, Database::class.java, Const.Database.DATABASE_NAME)
        .build()

    @Provides
    @Singleton
    fun provideWeatherDao(database: Database) = database.weatherDao()

    @Provides
    @Singleton
    fun provideWeatherLocalDataStore(weatherDao: WeatherDao): WeatherLocalDataStore =
        WeatherLocalDataStoreImpl(weatherDao)
}