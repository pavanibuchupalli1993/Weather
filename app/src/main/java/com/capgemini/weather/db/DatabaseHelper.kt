package com.capgemini.weather.db

import com.capgemini.weather.data.model.WeatherRoomDataModel


interface DatabaseHelper {

    suspend fun fetchSearchData(): List<WeatherRoomDataModel>

    suspend fun insert(weatherRoomDataModel: WeatherRoomDataModel)

}