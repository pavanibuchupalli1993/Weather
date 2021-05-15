package com.capgemini.weather.view.home

import com.capgemini.weather.BuildConfig
import com.capgemini.weather.data.api.WeatherApiRequest
import com.capgemini.weather.constants.AppConstants
import com.capgemini.weather.data.model.city_wheather.WeatherApiResponseModel
import com.capgemini.weather.data.model.search.SearchApiResponseModel
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class ApiRepository @Inject constructor(private val apiRequest: WeatherApiRequest) {

    fun search(query: String): Single<SearchApiResponseModel?>? {
        return apiRequest.search(BuildConfig.API_KEY, query, AppConstants.API_RESPONSE_FORMAT_JSON)
    }

    fun weather(query: String): Single<WeatherApiResponseModel?>? {
        return apiRequest.weather(BuildConfig.API_KEY, query, AppConstants.API_RESPONSE_FORMAT_JSON)
    }
}