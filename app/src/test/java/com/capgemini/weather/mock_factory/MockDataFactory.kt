package com.capgemini.weather.mock_factory

import com.capgemini.weather.data.model.WeatherRoomDataModel
import com.capgemini.weather.data.model.search.*


class MockDataFactory {

    companion object {

        fun getSearchApiResponseModel(): SearchApiResponseModel{
            val results: ArrayList<com.capgemini.weather.data.model.search.Result> = ArrayList()
            results.add(getResultModel())
            val searchApi = Search_api(results)
            return SearchApiResponseModel(searchApi);
        }

        fun getWeatherRoomDataModel(): WeatherRoomDataModel {
            val responseModel = WeatherRoomDataModel(
                "area, region", "area", "country",
                "region", "11.11", "22.22", "10000", "https://test.url", 1000L
            )
            return responseModel;
        }

        fun getWeatherRoomDataModel(id: String): WeatherRoomDataModel {
            val responseModel = WeatherRoomDataModel(
                id, "area", "country",
                "region", "11.11", "22.22", "10000", "https://test.url", 1000L
            )
            return responseModel;
        }

        fun getResultModel(): com.capgemini.weather.data.model.search.Result {
            val area = ArrayList<AreaName>()
            area.add(AreaName("area"))

            val country = ArrayList<Country>()
            country.add(Country("country"))

            val region = ArrayList<Region>()
            region.add(Region("region"))

            val weatherUrl = ArrayList<WeatherUrl>()
            weatherUrl.add(WeatherUrl("https://test.url"))

            return Result(area, country, region, 11.11, 22.22, 10000, weatherUrl);
        }


    }
}