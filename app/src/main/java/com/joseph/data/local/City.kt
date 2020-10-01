package com.joseph.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cities")
data class City(
    @PrimaryKey
    val id: Long,
    val name: String,
    val lat: Double?,
    val lon: Double?,
    val bookmarked: Boolean = false,
    val homeCity: Boolean = false
)