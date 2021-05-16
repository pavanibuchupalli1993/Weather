package com.capgemini.weather.db.room

import android.content.Context
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.capgemini.weather.data.dao.WeatherDao
import com.capgemini.weather.db.AppDatabase
import com.capgemini.weather.mock_factory.MockDataFactory
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class AppDatabaseTest : TestCase() {

    private lateinit var weatherDao: WeatherDao
    private lateinit var appDatabase: AppDatabase

    @Before
    public override fun setUp() {
        val context = mock(Context::class.java)
        appDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        weatherDao = appDatabase.weatherDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        appDatabase.close()
    }

    @Test
    fun `test DB insertSearchData and fetchSearchData queries`(): Unit = runBlocking {
        val weatherRoomDataModel = MockDataFactory.getWeatherRoomDataModel()
        weatherDao.insert(weatherRoomDataModel)
        val weatherRoomDataModels = weatherDao.fetchSearchData()
        Assertions.assertThat(weatherRoomDataModels.contains(weatherRoomDataModel)).isTrue
    }

    @Test
    fun `test DB duplicate insertSearchData query`(): Unit = runBlocking {
        val weatherRoomDataModel = MockDataFactory.getWeatherRoomDataModel()
        weatherDao.insert(weatherRoomDataModel)
        weatherDao.insert(weatherRoomDataModel)
        val weatherRoomDataModels = weatherDao.fetchSearchData()
        org.junit.jupiter.api.Assertions.assertEquals(weatherRoomDataModels.size, 1)
    }

    @Test
    fun `test DB fetchSearchData query should give only 10 results`(): Unit = runBlocking {
        for (i in 1 until 15) {
            val weatherRoomDataModel = MockDataFactory.getWeatherRoomDataModel("id-$i")
            weatherDao.insert(weatherRoomDataModel)
        }
        val weatherRoomDataModels = weatherDao.fetchSearchData()
        org.junit.jupiter.api.Assertions.assertEquals(weatherRoomDataModels.size, 10)
    }

}