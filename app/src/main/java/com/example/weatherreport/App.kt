package com.example.weatherreport

import android.app.Application
import com.example.weatherreport.di.AppComponent
import com.example.weatherreport.di.DaggerAppComponent
import com.example.weatherreport.di.modules.DataStoreModule
import com.example.weatherreport.di.modules.NavigationModule

class App: Application() {
    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .dataStoreModule(DataStoreModule(applicationContext))
            .navigationModule(NavigationModule())
            .build()
        appComponent.inject(this)
    }
}