package com.joseph.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(version = 1, entities = [Weather::class, City::class],exportSchema = false)
abstract class LocalDataSource : RoomDatabase() {
    abstract fun getWeatherDao(): WeatherDao

    abstract fun getCityDao(): CityDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: LocalDataSource? = null

        fun getDatabase(context: Context): LocalDataSource {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LocalDataSource::class.java,
                    "angani"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}