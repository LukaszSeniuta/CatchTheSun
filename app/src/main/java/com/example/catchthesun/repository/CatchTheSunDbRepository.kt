package com.example.catchthesun.repository

import com.example.catchthesun.data.CatchTheSunDao
import com.example.catchthesun.weather_model.Favourite
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CatchTheSunDbRepository @Inject constructor(private val catchTheSunDao: CatchTheSunDao) {

    fun getFavourites(): Flow<List<Favourite>> = catchTheSunDao.getFavourites()

    suspend fun deleteFavourite(favourite: Favourite) = catchTheSunDao.deleteFavourite(favourite)

    suspend fun insertFavourite(favourite: Favourite) = catchTheSunDao.insertFavourite(favourite)

    suspend fun deleteAllFavourites() = catchTheSunDao.deleteAllFavourites()

}