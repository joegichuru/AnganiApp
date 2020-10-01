package com.joseph.data.remote

import com.joseph.data.domain.WeatherResponse
import com.joseph.data.local.Weather
import com.joseph.utils.Constants
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataSource {
    private var servicesApiInterface: RemoteService? = null

    init {
        servicesApiInterface = build()
    }

    fun build(): RemoteService? {
        var builder: Retrofit.Builder = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())

        var httpClient: OkHttpClient.Builder = OkHttpClient.Builder()

        var retrofit: Retrofit = builder.client(httpClient.build()).build()
        servicesApiInterface = retrofit.create(
            RemoteService::class.java
        )

        return servicesApiInterface as RemoteService
    }

    fun fetchWeather(city: String): Observable<ResponseBody>? {
        return servicesApiInterface?.getWeather(city, Constants.API_KEY,"metric")
    }

    fun forecast(city: String): Observable<ResponseBody>? {
        return servicesApiInterface?.getForecast(city, Constants.API_KEY,"metric","7")
    }


}