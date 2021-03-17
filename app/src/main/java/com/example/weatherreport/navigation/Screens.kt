package com.example.weatherreport.navigation

import com.example.weatherreport.ui.login.LoginFragment
import com.example.weatherreport.ui.main.MainFragment
import com.example.weatherreport.ui.register.RegisterFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {
    fun login() = FragmentScreen { LoginFragment() }
    fun register(login: String, password: String) = FragmentScreen { RegisterFragment(login, password) }
    fun main() = FragmentScreen { MainFragment() }
}