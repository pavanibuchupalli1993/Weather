package com.capgemini.weather.data.model.search
import com.google.gson.annotations.SerializedName

data class SearchApiResponseModel (
	@SerializedName("search_api") val search_api : Search_api
)

data class AreaName (

	@SerializedName("value") val value : String
)

data class Country (

	@SerializedName("value") val value : String
)

data class Region (

	@SerializedName("value") val value : String
)
data class Result (
	@SerializedName("areaName") val areaName : List<AreaName>,
	@SerializedName("country") val country : List<Country>,
	@SerializedName("region") val region : List<Region>,
	@SerializedName("latitude") val latitude : Double,
	@SerializedName("longitude") val longitude : Double,
	@SerializedName("population") val population : Int,
	@SerializedName("weatherUrl") val weatherUrl : List<WeatherUrl>
) : java.io.Serializable

data class Search_api (

	@SerializedName("result") val result : List<Result>
)

data class WeatherUrl (
	@SerializedName("value") val value : String
)
