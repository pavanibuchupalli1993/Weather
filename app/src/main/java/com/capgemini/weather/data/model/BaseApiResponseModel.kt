package com.capgemini.weather.data.model

data class BaseApiResponseModel<T>(
    val isSuccessful: Boolean = false,
    var apiResponseData: Any? = null,
    val apiException: Any? = null
)
