package com.example.weatherreport.ui.cache

import com.example.weatherreport.data.weather.Weather
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface CacheView : MvpView {
    @AddToEndSingle
    fun initAdapter(items: List<Weather>)

    @AddToEndSingle
    fun fillAdapter(items: List<Weather>)
}