package com.joseph.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.joseph.data.domain.WeatherResponse
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {
    @Query("SELECT * FROM weather where city=:city")
    fun findByCity(city: String): Single<Weather>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(weather: Weather):Unit

    @Delete
    fun delete(weather: Weather)

    @Query("SELECT * FROM weather")
    fun findAll(): Single<List<Weather>>
}