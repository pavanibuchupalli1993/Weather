package com.capgemini.weather.view.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capgemini.weather.data.model.search.Result
import com.capgemini.weather.data.model.search.SearchApiResponseModel
import com.capgemini.weather.data.model.WeatherRoomDataModel
import com.capgemini.weather.db.DatabaseHelper
import com.capgemini.weather.view.home.ApiRepository
import com.capgemini.weather.utils.DataConverter
import com.capgemini.weather.utils.LiveDataUtils
import com.capgemini.weather.data.model.BaseApiResponseModel
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.launch
import java.util.ArrayList

class HomeViewModel(
    private val apiRepository: ApiRepository, private val dbHelper: DatabaseHelper
) : ViewModel() {

    private val cityMutableLiveData: MutableLiveData<BaseApiResponseModel<SearchApiResponseModel>> =
        MutableLiveData()

    fun getCityMutableLiveData(): MutableLiveData<BaseApiResponseModel<SearchApiResponseModel>> {
        return cityMutableLiveData;
    }

    private val dbDataMutableLiveData: MutableLiveData<List<WeatherRoomDataModel>> =
        MutableLiveData()

    fun getDbDataMutableLiveData(): MutableLiveData<List<WeatherRoomDataModel>> {
        return dbDataMutableLiveData;
    }

    fun searchProductsResponseLiveData(searchQuery: String) {
        val baseSearchApiResponseModelSingle: Single<SearchApiResponseModel?>? =
            apiRepository.search(searchQuery);
        LiveDataUtils.updateStatus(cityMutableLiveData, baseSearchApiResponseModelSingle)
    }

    fun fetchSearchedData() {
        viewModelScope.launch {
            try {
                val usersFromDb = dbHelper.fetchSearchData()
                dbDataMutableLiveData.postValue(usersFromDb)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun convertToDbModel(result: Result, dataConverter: DataConverter): WeatherRoomDataModel {
        return dataConverter.convertToDbModel(result)
    }

    fun insertData(weatherRoomDataModel: WeatherRoomDataModel) {
        viewModelScope.launch {
            try {
                dbHelper.insert(weatherRoomDataModel)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun filterData(it: Any, dataConverter: DataConverter): ArrayList<com.capgemini.weather.data.model.search.Result>? {
        return dataConverter.convertToSearchModelList(it as SearchApiResponseModel)
    }
}