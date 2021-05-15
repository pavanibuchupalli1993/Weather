package com.capgemini.weather.di.modules

import com.capgemini.weather.data.api.WeatherApiRetrofit
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class UtilsModule {

    @Provides
    fun provideRetrofit(weatherApiRetrofit: WeatherApiRetrofit): Retrofit {
        return weatherApiRetrofit.getRetrofitInstance()
    }
}