package com.capgemini.weather.view.weather_info

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capgemini.weather.data.model.BaseApiResponseModel
import com.capgemini.weather.data.model.city_wheather.Current_condition
import com.capgemini.weather.data.model.city_wheather.WeatherApiResponseModel
import com.capgemini.weather.data.model.WeatherRoomDataModel
import com.capgemini.weather.view.home.ApiRepository
import com.capgemini.weather.utils.LiveDataUtils
import io.reactivex.rxjava3.core.Single

class WeatherInfoViewModel(private val apiRepository: ApiRepository) : ViewModel() {

    fun getData(arguments: Bundle): WeatherRoomDataModel {
        return arguments.getSerializable("selectedCity") as WeatherRoomDataModel
    }

    fun getGeoLocationText(roomDataModel: WeatherRoomDataModel?): CharSequence? {
        return roomDataModel?.latitude.toString() + "," + roomDataModel?.longitude.toString()
    }

    private val weatherMutableLiveData: MutableLiveData<BaseApiResponseModel<WeatherApiResponseModel>> =
        MutableLiveData()

    fun getWeatherMutableLiveData(): MutableLiveData<BaseApiResponseModel<WeatherApiResponseModel>> {
        return weatherMutableLiveData;
    }

    fun fetchWeatherResponseLiveData(searchQuery: String) {
        val apiResponseModelSingle: Single<WeatherApiResponseModel?>? =
            apiRepository.weather(searchQuery);
        LiveDataUtils.updateStatus(weatherMutableLiveData, apiResponseModelSingle)
    }

    fun getTemperatureText(currentCondition: Current_condition): CharSequence? {
        return currentCondition.temp_C.toString() + " C"
    }
}