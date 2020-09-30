package com.joseph.data.local

import androidx.room.Entity
import java.util.*

@Entity(tableName = "weather")
data class Weather(
    val id: Long, val wind: Int, val temperature: Int,
    val precipitation: String, val forecast: String,
    val description: String,val city: City,val date: Date
)