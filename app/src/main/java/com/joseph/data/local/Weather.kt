package com.joseph.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "weather")
data class Weather(
    @PrimaryKey
    val id: Long,
    val wind: Double,
    val temperature: Double,
    val cloud: Int,
    val forecast: String,
    val description: String="",
    val city: String,
    val date: Long,
    val pressure: Double,
    val humidity: Int
)