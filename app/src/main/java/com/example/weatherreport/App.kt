package com.example.weatherreport

import android.app.Application
import com.example.weatherreport.di.DaggerAppComponent

class App: Application() {
    val appComponent = DaggerAppComponent.create()
}