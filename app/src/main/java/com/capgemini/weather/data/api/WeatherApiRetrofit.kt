package com.capgemini.weather.data.api

import android.content.Context
import com.capgemini.weather.BuildConfig
import retrofit2.Retrofit
import javax.inject.Inject

class WeatherApiRetrofit @Inject constructor(private val context: Context) : BaseRetrofit() {

    private lateinit var retrofit: Retrofit

    fun getRetrofitInstance(): Retrofit {
        retrofit =
            getRetrofitInstance(context, BuildConfig.API_URL)
        return retrofit
    }
}