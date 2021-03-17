package com.example.weatherreport.ui.cache

import com.example.weatherreport.data.weather.local.WeatherLocalDataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.InjectViewState
import moxy.MvpPresenter
import moxy.presenterScope
import javax.inject.Inject

@InjectViewState
class CachePresenter : MvpPresenter<CacheView>() {

    @Inject lateinit var weatherLocalDataStore: WeatherLocalDataStore

    fun initAdapter() {
        presenterScope.launch(Dispatchers.Default) {
            val items = weatherLocalDataStore.get()
            withContext(Dispatchers.Main) {
                viewState.initAdapter(items)
            }
        }
    }

    fun updateAdapter() {
        presenterScope.launch(Dispatchers.Default) {
            val items = weatherLocalDataStore.get()
            withContext(Dispatchers.Main) {
                viewState.fillAdapter(items)
            }
        }
    }
}