package com.capgemini.weather.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.capgemini.weather.data.model.WeatherRoomDataModel
import com.capgemini.weather.data.dao.WeatherDao

@Database(entities = [WeatherRoomDataModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun weatherDao(): WeatherDao

}