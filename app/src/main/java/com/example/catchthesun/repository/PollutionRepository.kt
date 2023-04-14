package com.example.catchthesun.repository

import android.util.Log
import com.example.catchthesun.data.DataOrException
import com.example.catchthesun.network.PollutionApi
import com.example.catchthesun.pollution_model.Pollution
import javax.inject.Inject

class PollutionRepository @Inject constructor(private val api: PollutionApi) {

    suspend fun getPollution(city: String)
            : DataOrException<Pollution, Boolean, Exception> {
        val response = try {
            api.getPollution(city = city)

        }catch (e: Exception){
            Log.d("REX", "getCity: $e")
            return DataOrException(e = e)
        }
        Log.d("INSIDE", "getCity: $response")
        return  DataOrException(data = response)

    }
}