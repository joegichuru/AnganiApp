package com.joseph.data.remote

import com.joseph.data.domain.WeatherResponse
import com.joseph.data.local.Weather
import io.reactivex.Observable
import okhttp3.ResponseBody

import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteService {
    @GET("weather")
    fun getWeather(@Query("q") city: String,@Query("appid") api:String,@Query("units") units:String ): Observable<ResponseBody>

    @GET("forecast")
    fun getForecast(@Query("q") city: String,@Query("appid") api:String): Observable<ResponseBody>
}