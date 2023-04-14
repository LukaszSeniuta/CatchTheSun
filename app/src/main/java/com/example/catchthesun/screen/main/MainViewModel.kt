package com.example.catchthesun.screen.main

import androidx.lifecycle.ViewModel
import com.example.catchthesun.city_model.City
import com.example.catchthesun.data.DataOrException
import com.example.catchthesun.pollution_model.Pollution
import com.example.catchthesun.repository.CityRepository
import com.example.catchthesun.repository.PollutionRepository
import com.example.catchthesun.repository.WeatherRepository
import com.example.catchthesun.weather_model.Weather
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val cityRepository: CityRepository,
    private val pollutionRepository: PollutionRepository
) : ViewModel() {

    suspend fun getWeatherData(lat: Double, lon: Double):
            DataOrException<Weather, Boolean, Exception> {

        return weatherRepository.getWeather(lat = lat, lon = lon)
    }

    suspend fun getCityData(city: String):
            DataOrException<City, Boolean, Exception> {

        return cityRepository.getCity(query = city)
    }

    suspend fun getPollutionData(city: String):
            DataOrException<Pollution, Boolean, Exception> {

        return pollutionRepository.getPollution(city = city)
    }

}