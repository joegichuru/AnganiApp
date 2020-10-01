package com.joseph.ui

import android.app.Application
import android.content.Context
import android.net.NetworkInfo
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.joseph.data.local.LocalDataSource
import com.joseph.data.remote.RemoteDataSource
import com.joseph.data.repository.WeatherRepository
import com.joseph.utils.Utils

class WeatherViewModel(application: Application) : AndroidViewModel(application) {
    private var weatherRepository: WeatherRepository = WeatherRepository(
        LocalDataSource.getDatabase(getApplication()),
        RemoteDataSource()
    )

    override fun onCleared() {
        super.onCleared()
    }

    init {
        Utils.currentCity(getApplication())?.let {
            weatherRepository.getCurrentWeather(it, true)
        }
    }

    val getWeather = weatherRepository.weather
}