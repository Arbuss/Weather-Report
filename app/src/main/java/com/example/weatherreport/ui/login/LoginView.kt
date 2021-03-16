package com.example.weatherreport.ui.login

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface LoginView: MvpView {
    @AddToEndSingle
    fun highlightLoginField()

    @AddToEndSingle
    fun highlightPasswordField()
}