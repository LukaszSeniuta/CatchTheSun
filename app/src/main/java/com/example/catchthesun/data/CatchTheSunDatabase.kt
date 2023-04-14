package com.example.catchthesun.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.catchthesun.weather_model.Favourite

@Database(entities = [Favourite::class], version = 1, exportSchema = false)
abstract class CatchTheSunDatabase: RoomDatabase() {

    abstract fun catchTheSunDao(): CatchTheSunDao

}