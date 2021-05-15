package com.capgemini.weather.di

import dagger.android.AndroidInjector
import com.capgemini.weather.di.components.DaggerAppComponent
import com.facebook.stetho.Stetho
import dagger.android.DaggerApplication

class WeatherApplication : DaggerApplication() {
    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
    }

    override fun applicationInjector(): AndroidInjector<WeatherApplication?> {
        return DaggerAppComponent.factory().create(this)
    }
}