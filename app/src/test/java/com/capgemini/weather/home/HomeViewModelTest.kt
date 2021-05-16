package com.capgemini.weather.home

import com.capgemini.weather.db.DatabaseHelper
import com.capgemini.weather.mock_factory.MockDataFactory
import com.capgemini.weather.utils.DataConverter
import com.capgemini.weather.utils.InstantExecutorExtension
import com.capgemini.weather.view.home.ApiRepository
import com.capgemini.weather.view.home.viewmodel.HomeViewModel
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.reactivex.rxjava3.core.Single
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantExecutorExtension::class)
class HomeViewModelTest {

    lateinit var viewModel: HomeViewModel

    @MockK(relaxed = true)
    lateinit var apiRepository: ApiRepository

    @MockK(relaxed = true)
    lateinit var dbHelper: DatabaseHelper


    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        viewModel = HomeViewModel(apiRepository, dbHelper)
    }

    @Test
    fun `test search api`() {
        val apiResponseModel = MockDataFactory.getSearchApiResponseModel()
        every {
            apiRepository.search(any())
        } returns Single.just(apiResponseModel)

        viewModel.searchProductsResponseLiveData("123")

        Assertions.assertEquals(
            apiResponseModel,
            viewModel.getCityMutableLiveData().value?.apiResponseData
        )
    }


    @Test
    fun `test convert to DB Model`() {
        val dataConverter = DataConverter()
        val actualWeatherRoomDataModel = MockDataFactory.getWeatherRoomDataModel()
        val result = MockDataFactory.getResultModel()
        val expectedWeatherModel = viewModel.convertToDbModel(result, dataConverter)

        Assertions.assertEquals(expectedWeatherModel.id, actualWeatherRoomDataModel.id)
        Assertions.assertEquals(expectedWeatherModel.areaName, actualWeatherRoomDataModel.areaName)
        Assertions.assertEquals(expectedWeatherModel.country, actualWeatherRoomDataModel.country)
        Assertions.assertEquals(expectedWeatherModel.region, actualWeatherRoomDataModel.region)
        Assertions.assertEquals(expectedWeatherModel.latitude, actualWeatherRoomDataModel.latitude)
        Assertions.assertEquals(expectedWeatherModel.longitude, actualWeatherRoomDataModel.longitude)
        Assertions.assertEquals(expectedWeatherModel.weatherUrl, actualWeatherRoomDataModel.weatherUrl)
    }



}