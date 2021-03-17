package com.example.weatherreport.di

import com.example.weatherreport.App
import com.example.weatherreport.MainActivity
import com.example.weatherreport.di.modules.DataStoreModule
import com.example.weatherreport.di.modules.NavigationModule
import com.example.weatherreport.di.modules.NetworkingModule
import com.example.weatherreport.ui.cache.CacheFragment
import com.example.weatherreport.ui.cache.CachePresenter
import com.example.weatherreport.ui.login.LoginFragment
import com.example.weatherreport.ui.login.LoginPresenter
import com.example.weatherreport.ui.main.MainFragment
import com.example.weatherreport.ui.register.RegisterFragment
import com.example.weatherreport.ui.register.RegisterPresenter
import com.example.weatherreport.ui.search.SearchFragment
import com.example.weatherreport.ui.search.SearchPresenter
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        DataStoreModule::class,
        NavigationModule::class,
        NetworkingModule::class
    ]
)
@Singleton
interface AppComponent {
    fun inject(application: App)
    fun inject(activity: MainActivity)

    fun inject(fragment: LoginFragment)
    fun inject(fragment: RegisterFragment)
    fun inject(fragment: MainFragment)
    fun inject(fragment: SearchFragment)
    fun inject(fragment: CacheFragment)

    fun inject(presenter: LoginPresenter)
    fun inject(presenter: RegisterPresenter)
    fun inject(presenter: SearchPresenter)
    fun inject(presenter: CachePresenter)
}