package com.example.catchthesun.weather_model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "fav_tbl")
data class Favourite(

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "city")
    val city: String
)
