package com.joseph.data.local

data class Forecast(val current: Weather, val weekly: Array<TimeData>) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Forecast

        if (current != other.current) return false
        if (!weekly.contentEquals(other.weekly)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = current.hashCode()
        result = 31 * result + weekly.contentHashCode()
        return result
    }
}

data class TimeData(val time: Long, val weather: Weather)