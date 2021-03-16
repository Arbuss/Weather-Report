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
        if(!loginPasswordValid(login, password)) return
        if(usersDataStore.login(login, password)) {
            onSignInSuccess()
        } else {
            onSignInError()
        }
    }

    private fun onSignInSuccess() {
        router.replaceScreen(Screens.main())
    }

    private fun onSignInError() {
        router.replaceScreen(Screens.register())
    }

    private fun loginPasswordValid(login: String, password: String): Boolean {
        if(!loginValid(login)) {
            viewState.highlightLoginField()
            return false
        }
        if(!passwordValid(password)) {
            viewState.highlightPasswordField()
            return false
        }
        return true
    }

    private fun loginValid(login: String) = login.isNotEmpty()

    private fun passwordValid(password: String) = password.isNotEmpty()
}