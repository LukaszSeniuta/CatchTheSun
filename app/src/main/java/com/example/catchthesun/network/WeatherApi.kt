package com.example.catchthesun.network

import com.example.catchthesun.weather_model.Weather
import com.example.catchthesun.utils.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface WeatherApi {

    @GET(value = "data/3.0/onecall")
    suspend fun getWeather(
        @Query("lat") lat : Double,
        @Query("lon") lon : Double,
        @Query("units") units: String = "metric",
        @Query("lang") lang: String = "pl",
        @Query("appid") appid: String = API_KEY
    ): Weather
}