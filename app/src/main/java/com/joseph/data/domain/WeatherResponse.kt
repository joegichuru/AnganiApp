package com.joseph.data.domain

import java.io.Serializable


class WeatherResponse(
    val id: Long, val weather: Array<WeatherInternal>, val main: Main,
    val dt: Long, val name: String, val clouds: Clouds, val wind: Wind
) : Serializable

data class WeatherInternal(val id: Long, val main: String, val description: String) : Serializable

data class Main(
    val temp: Double, val feelsLike: Double, val tempMin: Double, val tempMax: Double,
    val humidity: Int, val pressure: Double
) : Serializable

data class Clouds(val all: Int) : Serializable

data class Wind(val speed: Double, val deg: Double) : Serializable