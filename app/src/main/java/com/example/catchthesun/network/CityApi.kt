package com.example.catchthesun.network

import com.example.catchthesun.city_model.City
import com.example.catchthesun.utils.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface CityApi {

    @GET(value = "geo/1.0/direct")
    suspend fun getCity(
        @Query("q") query : String,
        @Query("limit") limit: Int = 1,
        @Query("appid") appid: String = API_KEY
    ): City
}