package com.example.weatherreport.di.modules

import android.content.Context
import androidx.preference.PreferenceManager
import com.example.weatherreport.data.user.UsersDataStore
import com.example.weatherreport.data.user.UsersDataStoreImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataStoreModule {
    @Provides
    @Singleton
    fun provideSharedPreferences(context: Context) = PreferenceManager.getDefaultSharedPreferences(context)

    @Provides
    @Singleton
    fun provideUsersDataStore(): UsersDataStore = UsersDataStoreImpl()
}