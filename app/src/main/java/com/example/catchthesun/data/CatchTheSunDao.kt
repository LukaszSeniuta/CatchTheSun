package com.example.catchthesun.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.catchthesun.weather_model.Favourite
import kotlinx.coroutines.flow.Flow


@Dao
interface CatchTheSunDao {

    @Query("SELECT * from fav_tbl")
    fun getFavourites(): Flow<List<Favourite>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavourite(favourite:Favourite)

    @Query("DELETE from fav_tbl")
    suspend fun deleteAllFavourites()

    @Delete
    suspend fun deleteFavourite(favourite: Favourite)
}