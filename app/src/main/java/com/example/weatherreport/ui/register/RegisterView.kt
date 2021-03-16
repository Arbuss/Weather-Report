package com.example.weatherreport.ui.register

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface RegisterView : MvpView {
    @AddToEndSingle
    fun highlightPasswordConfirmationField()

    @AddToEndSingle
    fun highlightPasswordsFields()
}