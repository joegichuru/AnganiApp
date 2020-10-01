package com.joseph.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.joseph.data.domain.WeatherResponse
import com.joseph.data.local.Forecast
import com.joseph.data.local.LocalDataSource
import com.joseph.data.local.Weather
import com.joseph.data.remote.RemoteDataSource
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import org.json.JSONObject


class WeatherRepository constructor(
    val localDataSource: LocalDataSource,
    val remoteDataSource: RemoteDataSource
) {
    val weather: MutableLiveData<Weather> = MutableLiveData()
    val error: MutableLiveData<String> = MutableLiveData()


    fun insertWeather(weather: Weather) {
        Completable.fromAction({
            localDataSource.getWeatherDao().insert(weather)
        }).observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe { {};{} }
    }

    fun deleteWeather(weather: Weather) {
        localDataSource.getWeatherDao().delete(weather)
    }

    fun getCurrentWeather(city: String, shouldFetch: Boolean) {
        Log.i("data", "getting data")
        return if (shouldFetch) {
            fetchWeather(city)
        } else {
            getLocalWeather(city)
        }
    }

    private fun fetchWeather(city: String) {
        Log.i("data", "fetch $city")
        remoteDataSource.fetchWeather(city)
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeOn(Schedulers.io())
            ?.doOnSubscribe(Consumer {
                Log.i("data", "subscribed")
            })
            ?.subscribe({
                val gson = Gson()
                val fromJson = gson.fromJson(it.string(), WeatherResponse::class.java)
                val w = convertToWeather(fromJson)
                Log.i("DATA", w.toString())
                weather.postValue(w)
                insertWeather(w)
            }, { it.printStackTrace() }, { Log.i("data", "done") })
    }

    fun getLocalWeather(city: String) {
        localDataSource.getWeatherDao().findByCity(city)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            ?.doOnError {
                it.stackTrace
            }
            .subscribe(Consumer {
                weather.postValue(it)
            }, Consumer {
                it.stackTrace
            })

    }


    fun convertToWeather(weatherResponse: WeatherResponse): Weather {
        return Weather(
            id = weatherResponse.id,
            wind = weatherResponse.wind.speed,
            temperature = weatherResponse.main.temp,
            city = weatherResponse.name,
            cloud = weatherResponse.clouds.all,
            date = weatherResponse.dt,
            description = weatherResponse.weather[0].description,
            forecast = weatherResponse.weather[0].main,
            humidity = weatherResponse.main.humidity,
            pressure = weatherResponse.main.pressure
        )
    }
}