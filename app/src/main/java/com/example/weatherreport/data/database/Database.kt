package com.example.weatherreport.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.weatherreport.data.database.dao.WeatherDao
import com.example.weatherreport.data.database.entity.WeatherEntity

@Database(
    entities = [
        WeatherEntity::class
    ], exportSchema = false, version = 1
)
abstract class Database : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}