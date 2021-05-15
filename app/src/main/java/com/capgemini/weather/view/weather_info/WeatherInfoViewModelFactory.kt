package com.capgemini.weather.view.weather_info

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.capgemini.weather.view.home.ApiRepository

class WeatherInfoViewModelFactory(
    private val apiRepository: ApiRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return WeatherInfoViewModel(apiRepository) as T
    }
}