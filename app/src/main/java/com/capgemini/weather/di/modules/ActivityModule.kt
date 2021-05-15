package com.capgemini.weather.di.modules


import com.capgemini.weather.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityModule {
    @ContributesAndroidInjector
    fun mainActivity(): MainActivity?
}