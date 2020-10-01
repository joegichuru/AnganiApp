package com.joseph.data.local

data class Forecast(val current: Weather?, val weekly: MutableList<TimeData?>)

data class TimeData(val time: Long, val weather: Weather)