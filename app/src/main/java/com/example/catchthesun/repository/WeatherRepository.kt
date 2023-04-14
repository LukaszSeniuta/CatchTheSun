package com.example.catchthesun.repository

import android.util.Log
import com.example.catchthesun.data.DataOrException
import com.example.catchthesun.network.WeatherApi
import com.example.catchthesun.weather_model.Weather
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val api: WeatherApi) {

    suspend fun getWeather(lat: Double, lon: Double):
            DataOrException<Weather, Boolean, Exception> {

        val response = try {

            api.getWeather(lat = lat, lon = lon)


        } catch (e: Exception) {
            Log.d("REX", "getWeather: $e")
            return DataOrException(e = e)
        }
        Log.d("INSIDE", "getWeather: $response")
        return DataOrException(data = response)

    }

}