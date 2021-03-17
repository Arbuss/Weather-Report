package com.example.weatherreport.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weatherreport.data.database.entity.WeatherEntity

@Dao
interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: WeatherEntity)

    @Query("select * from Weather")
    fun get(): List<WeatherEntity>

    @Query("select * from Weather where id = :id")
    fun get(id: Long): WeatherEntity
}