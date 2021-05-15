package com.capgemini.weather.di.modules

import com.capgemini.weather.view.weather_info.WeatherInfoFragment
import com.capgemini.weather.view.home.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentModule {

    @ContributesAndroidInjector
    fun homeFragment(): HomeFragment?

    @ContributesAndroidInjector
    fun detailsFragment(): WeatherInfoFragment?
}