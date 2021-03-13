package com.example.weatherreport.di.modules

import com.example.weatherreport.Const
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class NetworkingModule {
    @Provides
    @Singleton
    fun provideRetrofit() = Retrofit.Builder().baseUrl(Const.Networking.BASE_URL).build()
}