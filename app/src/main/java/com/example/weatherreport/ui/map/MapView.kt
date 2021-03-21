package com.example.weatherreport.ui.map

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface MapView : MvpView {
    @AddToEndSingle
    fun setCurrentLocation()

    @AddToEndSingle
    fun checkPermissions(onSuccess: (() -> Unit)?, onError: (() -> Unit)?)

    @AddToEndSingle
    fun requestMapPermissions()
}