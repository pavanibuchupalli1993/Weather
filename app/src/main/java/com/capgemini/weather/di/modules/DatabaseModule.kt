package com.capgemini.weather.di.modules

import android.content.Context
import com.capgemini.weather.db.DatabaseBuilder
import com.capgemini.weather.db.DatabaseHelperImpl
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {

    @Provides
    fun provideDBHelper(context : Context): DatabaseHelperImpl {
        return DatabaseHelperImpl(DatabaseBuilder.getInstance(context))
    }
}