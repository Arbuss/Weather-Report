package com.example.weatherreport.navigation

import com.example.weatherreport.ui.login.LoginFragment
import com.example.weatherreport.ui.register.RegisterFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {
    fun login() = FragmentScreen { LoginFragment() }
    fun register() = FragmentScreen { RegisterFragment() }
}