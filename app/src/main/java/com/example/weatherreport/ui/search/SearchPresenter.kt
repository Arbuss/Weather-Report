package com.example.weatherreport.ui.search

import com.example.weatherreport.data.weather.local.WeatherLocalDataStore
import com.example.weatherreport.data.weather.remote.WeatherRemoteDataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.InjectViewState
import moxy.MvpPresenter
import moxy.presenterScope
import javax.inject.Inject

@InjectViewState
class SearchPresenter: MvpPresenter<SearchView>() {

    @Inject
    lateinit var remoteDataStore: WeatherRemoteDataStore

    @Inject
    lateinit var localDataStore: WeatherLocalDataStore

    fun search(city: String, units: String, lang: String) {
        presenterScope.launch(Dispatchers.Default) {
            val weather = remoteDataStore.get(city, units, lang)
            withContext(Dispatchers.Main) {
                weather?.let {
                    viewState.onSuccess(it)
                    localDataStore.add(it)
                } ?: viewState.onError()
            }
        }
    }
}