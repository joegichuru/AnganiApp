package com.joseph.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Single

@Dao
interface CityDao {
    @Insert
    fun insert(city: City)

    @Delete
    fun delete(city: City)

    @Query("SELECT * FROM cities")
    fun findAll(): Single<List<City>>

    @Query("SELECT * FROM CITIES c WHERE c.name=:name")
    fun findByName(name: String): Single<City>
}