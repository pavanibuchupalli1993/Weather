package com.capgemini.weather.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.capgemini.weather.data.model.WeatherRoomDataModel

@Dao
interface WeatherDao {

    @Query("SELECT * FROM WeatherTable ORDER BY timestamp DESC LIMIT 10")
    suspend fun fetchSearchData(): List<WeatherRoomDataModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(roomDataModel: WeatherRoomDataModel)
}