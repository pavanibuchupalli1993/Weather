package com.capgemini.weather.db

import com.capgemini.weather.data.model.WeatherRoomDataModel


class DatabaseHelperImpl(private val appDatabase: AppDatabase) : DatabaseHelper {

    override suspend fun fetchSearchData(): List<WeatherRoomDataModel> = appDatabase.weatherDao().fetchSearchData()

    override suspend fun insert(weatherRoomDataModel: WeatherRoomDataModel) = appDatabase.weatherDao().insert(weatherRoomDataModel)

}