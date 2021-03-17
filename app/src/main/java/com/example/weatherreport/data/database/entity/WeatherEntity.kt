package com.example.weatherreport.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Weather")
data class WeatherEntity (
    @PrimaryKey(autoGenerate = true) val id: Long,
    val cityName: String,
    val date: Long,
    val temperature: Double,
    val humidity: Int,
    val clouds: Int
)