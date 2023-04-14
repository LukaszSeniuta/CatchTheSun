package com.example.catchthesun.repository

import android.util.Log
import com.example.catchthesun.city_model.City
import com.example.catchthesun.data.DataOrException
import com.example.catchthesun.network.CityApi
import javax.inject.Inject

class CityRepository @Inject constructor(private val api: CityApi){

    suspend fun getCity(query: String)
            : DataOrException<City, Boolean, Exception> {
        val response = try {
            api.getCity(query = query)

        }catch (e: Exception){
            Log.d("REX", "getCity: $e")
            return DataOrException(e = e)
        }
        Log.d("INSIDE", "getCity: $response")
        return  DataOrException(data = response)

    }
}