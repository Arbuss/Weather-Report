package com.example.weatherreport.data.user

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject

class UsersDataStoreImpl @Inject constructor(private val sharedPreferences: SharedPreferences): UsersDataStore {

    override var currentUser: String? = null
        private set

    override fun login(login: String, password: String): Boolean {
        if(!userExists(login)) return false
        if(sharedPreferences.getString(login, "") != password) return false
        currentUser = login
        return true
    }

    override fun register(login: String, password: String): Boolean {
        if(userExists(login)) return false
        sharedPreferences.edit { putString(login, password) }
        currentUser = login
        return true
    }

    override fun userExists(login: String) = sharedPreferences.contains(login)
}