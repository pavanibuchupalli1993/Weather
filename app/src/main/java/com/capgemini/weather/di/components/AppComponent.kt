package com.capgemini.weather.di.components

import com.capgemini.weather.di.AppModule
import com.capgemini.weather.di.WeatherApplication
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent : AndroidInjector<WeatherApplication?> {
    @Component.Factory
    interface Factory : AndroidInjector.Factory<WeatherApplication?>
}