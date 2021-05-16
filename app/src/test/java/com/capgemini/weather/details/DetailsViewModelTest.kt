package com.capgemini.weather.details


import com.capgemini.weather.mock_factory.DetailsMockDataFactory
import com.capgemini.weather.mock_factory.MockDataFactory
import com.capgemini.weather.utils.InstantExecutorExtension
import com.capgemini.weather.view.home.ApiRepository
import com.capgemini.weather.view.weather_info.WeatherInfoViewModel
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.reactivex.rxjava3.core.Single
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantExecutorExtension::class)
class DetailsViewModelTest {

    lateinit var viewModel: WeatherInfoViewModel

    @MockK(relaxed = true)
    lateinit var apiRepository: ApiRepository


    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        viewModel = WeatherInfoViewModel(apiRepository)
    }

    @Test
    fun `test weather details api`() {
        val weatherApiResponseModel = DetailsMockDataFactory.getWeatherApiResponseModel()
        every {
            apiRepository.weather(any())
        } returns Single.just(weatherApiResponseModel)

        viewModel.fetchWeatherResponseLiveData("123")

        Assertions.assertEquals(
            weatherApiResponseModel,
            viewModel.getWeatherMutableLiveData().value?.apiResponseData
        )
    }

    @Test
    fun `test geo location text`() {
        val expectedValue = "11.11,22.22"
        val roomDataModel = MockDataFactory.getWeatherRoomDataModel()
        val actual = viewModel.getGeoLocationText(roomDataModel)
        Assertions.assertEquals(expectedValue, actual)
    }

    @Test
    fun `test temperature text`() {
        val expectedValue = "37 C"
        val currentCondition = DetailsMockDataFactory.getCurrentCondition()
        val actual = viewModel.getTemperatureText(currentCondition)
        Assertions.assertEquals(expectedValue, actual)
    }


}