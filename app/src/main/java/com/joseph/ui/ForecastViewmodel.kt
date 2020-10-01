package com.joseph.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.joseph.data.local.Forecast
import com.joseph.data.local.LocalDataSource
import com.joseph.data.remote.RemoteDataSource
import com.joseph.data.repository.ForecastRepository
import com.joseph.data.repository.WeatherRepository
import com.joseph.utils.Utils

class ForecastViewModel(application: Application) : AndroidViewModel(application) {
    private var forecastRepository: ForecastRepository = ForecastRepository(
        LocalDataSource.getDatabase(getApplication()),
        RemoteDataSource()
    )
    init {
        Utils.currentCity(getApplication())?.let {
            forecastRepository.fetchForecast(it)
        }
    }
    fun getForecast(): MutableLiveData<Forecast> {
        return forecastRepository.forecast
    }
}