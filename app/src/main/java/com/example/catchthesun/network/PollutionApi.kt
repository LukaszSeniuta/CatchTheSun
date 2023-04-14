package com.example.catchthesun.network

import com.example.catchthesun.pollution_model.Pollution
import com.example.catchthesun.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface PollutionApi {


    @GET(value = "feed/")
    suspend fun getPollution(
        @Query("") city : String,
        @Query("/?token") appid: String = Constants.POLLUTION_API_KEY
    ): Pollution
}
