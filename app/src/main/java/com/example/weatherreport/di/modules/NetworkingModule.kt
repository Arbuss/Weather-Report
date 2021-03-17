package com.example.weatherreport.di.modules

import com.example.weatherreport.Const
import com.example.weatherreport.data.weather.remote.networking.WeatherApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkingModule {
    @Provides
    @Singleton
    fun provideRetrofit() =
        Retrofit.Builder().baseUrl(Const.Networking.BASE_URL).addConverterFactory(
            GsonConverterFactory.create()
        ).build()

    @Provides
    @Singleton
    fun provideWeatherReportApi(retrofit: Retrofit) = retrofit.create(WeatherApi::class.java)
}