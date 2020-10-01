package com.joseph.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.joseph.data.local.Forecast
import com.joseph.data.local.LocalDataSource
import com.joseph.data.remote.RemoteDataSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class ForecastRepository constructor(
    val localDataSource: LocalDataSource,
    val remoteDataSource: RemoteDataSource
) {
    val forecast: MutableLiveData<Forecast> = MutableLiveData()
    fun fetchForecast(city: String) {
        remoteDataSource.forecast(city)
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeOn(Schedulers.io())
            ?.subscribe(Consumer {
                //insert data to live data
                Log.i("forecast", it.string())
            }, Consumer { it.printStackTrace() }, {})
    }

}