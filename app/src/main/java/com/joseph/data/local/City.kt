package com.joseph.data.local

import androidx.room.Entity

@Entity(tableName = "cities")
data class City(
    val id: Long,
    val name: String,
    val lat: Double?,
    val lon: Double?,
    val bookmarked: Boolean = false,
    val homeCity: Boolean = false
)