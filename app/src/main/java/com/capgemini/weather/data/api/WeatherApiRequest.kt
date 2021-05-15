package com.capgemini.weather.data.api

import com.capgemini.weather.data.model.city_wheather.WeatherApiResponseModel
import com.capgemini.weather.data.model.search.SearchApiResponseModel
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiRequest {

    @GET("premium/v1/search.ashx?")
    fun search(@Query("key") key : String,@Query("q") queryText : String,@Query("format") format : String, ): Single<SearchApiResponseModel?>?

    @GET("premium/v1/weather.ashx?")
    fun weather(@Query("key") key : String,@Query("q") queryText : String,@Query("format") format : String, ): Single<WeatherApiResponseModel?>?


}