package com.capgemini.weather.view.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.capgemini.weather.db.DatabaseHelper
import com.capgemini.weather.view.home.ApiRepository

class HomeViewModelFactory(
    private val apiRepository: ApiRepository,
    private val dbHelper: DatabaseHelper
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(apiRepository, dbHelper) as T
    }
}