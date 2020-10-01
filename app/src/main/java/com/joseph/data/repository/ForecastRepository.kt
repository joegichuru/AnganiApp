package com.joseph.data.repository

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.joseph.data.domain.WeatherResponse
import com.joseph.data.local.Forecast
import com.joseph.data.local.LocalDataSource
import com.joseph.data.local.TimeData
import com.joseph.data.local.Weather
import com.joseph.data.remote.RemoteDataSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import org.json.JSONArray
import org.json.JSONObject

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
                val data = JSONObject(it.string())
                //now insert
                forecast.postValue(convertForecast(data, city))
            }, Consumer { it.printStackTrace() }, {})
    }

    private fun convertForecast(data: JSONObject, city: String): Forecast {
        val array: JSONArray = data.getJSONArray("list")
        var list: MutableList<TimeData?> = mutableListOf()
        for (i in 0 until array.length()) {
            val dayData = array.getJSONObject(i)
            val gson: Gson = Gson()
            val wr = gson.fromJson(dayData.toString(), WeatherResponse::class.java)
            val weather = convertToWeather(wr, city)
            list.add(i,TimeData(weather = weather, time = weather.date))
        }
        return Forecast(null, list)
    }

    fun convertToWeather(weatherResponse: WeatherResponse, city: String): Weather {
        return Weather(
            id = weatherResponse.id,
            wind = weatherResponse.wind.speed,
            temperature = weatherResponse.main.temp,
            city = city,
            cloud = weatherResponse.clouds.all,
            date = weatherResponse.dt,
            description = weatherResponse.weather[0].description,
            forecast = weatherResponse.weather[0].main,
            humidity = weatherResponse.main.humidity,
            pressure = weatherResponse.main.pressure,
            icon = weatherResponse.weather[0].icon
        )
    }

}