package com.capgemini.weather.utils

import com.capgemini.weather.data.model.search.Result
import com.capgemini.weather.data.model.search.SearchApiResponseModel
import com.capgemini.weather.data.model.WeatherRoomDataModel
import javax.inject.Inject

class DataConverter @Inject constructor() {
    fun convertToSearchModelList(searchApiResponseData: SearchApiResponseModel?): ArrayList<Result>? {
        if (searchApiResponseData != null && searchApiResponseData.search_api != null && searchApiResponseData.search_api.result != null) {
            return searchApiResponseData.search_api.result as ArrayList
        }

        return ArrayList()
    }

    fun convertToDbModel(result: com.capgemini.weather.data.model.search.Result): WeatherRoomDataModel {

        return WeatherRoomDataModel(
            prepareDatabaseIdFromResult(result),
            result.areaName[0].value,
            result.country[0].value,
            result.region[0].value,
            result.latitude.toString(),
            result.longitude.toString(),
            result.population.toString(),
            result.weatherUrl[0].value,
            System.currentTimeMillis()
        )
    }

    fun prepareDatabaseIdFromResult(result: com.capgemini.weather.data.model.search.Result): String {
        return result.areaName[0].value + ", " + result.region[0].value
    }

    fun prepareDisplayIdFromResult(result: com.capgemini.weather.data.model.search.Result): String {
        return result.areaName[0].value + ", " + result.region[0].value + ", " + result.country[0].value
    }

}