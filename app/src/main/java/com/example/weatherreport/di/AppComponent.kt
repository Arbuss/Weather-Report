package com.example.weatherreport.di

import com.example.weatherreport.MainActivity
import dagger.Component

@Component(modules = [

])
interface AppComponent {
    fun inject(activity: MainActivity)

    fun inject()
}