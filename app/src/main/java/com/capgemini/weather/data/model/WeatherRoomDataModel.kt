package com.capgemini.weather.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "WeatherTable")
data class WeatherRoomDataModel(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "areaName") val areaName: String?,
    @ColumnInfo(name = "country") val country: String?,
    @ColumnInfo(name = "region") val region: String?,
    @ColumnInfo(name = "latitude") val latitude: String?,
    @ColumnInfo(name = "longitude") val longitude: String?,
    @ColumnInfo(name = "population") val population: String?,
    @ColumnInfo(name = "weatherUrl") val weatherUrl: String?,
    @ColumnInfo(name = "timestamp") val timestamp: Long?

) : Serializable