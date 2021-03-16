package com.example.weatherreport.ui.register

import com.example.weatherreport.data.user.UsersDataStore
import com.example.weatherreport.navigation.Screens
import com.github.terrakok.cicerone.Router
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class RegisterPresenter : MvpPresenter<RegisterView>() {

    @Inject
    lateinit var router: Router
    @Inject
    lateinit var usersDataStore: UsersDataStore

    fun register(login: String, password: String, passwordConfirmation: String) {
        if (!passwordsValid(password, passwordConfirmation)) return
        if (usersDataStore.register(login, password)) {
            onRegisterSuccess()
        }
    }

    private fun onRegisterSuccess() {
    }

    private fun passwordsValid(password: String, passwordConfirmation: String): Boolean {
        if (!passwordValid(passwordConfirmation)) {
            viewState.highlightPasswordConfirmationField()
            return false
        }
        if(!passwordMatching(password, passwordConfirmation)) {
            viewState.highlightPasswordsFields()
            return false
        }
        return true
    }

    private fun passwordMatching(password: String, passwordConfirmation: String) =
        password == passwordConfirmation

    private fun passwordValid(password: String) = password.isNotEmpty()
}