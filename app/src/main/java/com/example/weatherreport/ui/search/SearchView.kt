package com.example.weatherreport.ui.search

import com.example.weatherreport.data.weather.Weather
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface SearchView : MvpView {
    @AddToEndSingle
    fun onSuccess(weather: Weather)

    @AddToEndSingle
    fun onError()
}