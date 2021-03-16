package com.example.weatherreport.ui.login

import com.example.weatherreport.data.user.UsersDataStore
import com.example.weatherreport.navigation.Screens
import com.github.terrakok.cicerone.Router
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class LoginPresenter: MvpPresenter<LoginView>() {

    @Inject lateinit var router: Router
    @Inject lateinit var usersDataStore: UsersDataStore

    fun signIn(login: String, password: String) {
        if(!loginPasswordIsValid(login, password)) return
        if(usersDataStore.login(login, password)) {
            onSignInSuccess()
        } else {
            onSignInError()
        }
    }

    private fun onSignInSuccess() {
    }

    private fun onSignInError() {
    }

    private fun loginPasswordIsValid(login: String, password: String): Boolean {
        if(!loginIsValid(login)) {
            viewState.highlightLoginField()
            return false
        }
        if(!passwordIsValid(password)) {
            viewState.highlightPasswordField()
            return false
        }
        return true
    }

    private fun loginIsValid(login: String) = login.isNotEmpty()

    private fun passwordIsValid(password: String) = password.isNotEmpty()
}