package com.example.weatherreport.di

import com.example.weatherreport.MainActivity
import com.example.weatherreport.di.modules.DataStoreModule
import com.example.weatherreport.di.modules.NavigationModule
import com.example.weatherreport.di.modules.NetworkingModule
import com.example.weatherreport.ui.login.LoginFragment
import com.example.weatherreport.ui.main.MainFragment
import com.example.weatherreport.ui.register.RegisterFragment
import dagger.Component
import javax.inject.Singleton

@Component(modules = [
    DataStoreModule::class,
    NavigationModule::class,
    NetworkingModule::class
])
@Singleton
interface AppComponent {
    fun inject(activity: MainActivity)

    fun inject(fragment: LoginFragment)
    fun inject(fragment: RegisterFragment)
    fun inject(fragment: MainFragment)
}