package com.example.weatherreport.data.user

interface UsersDataStore {
    val currentUser: String?

    fun login(login: String, password: String): Boolean
    fun register(login: String, password: String): Boolean

    fun userExists(login: String): Boolean
}