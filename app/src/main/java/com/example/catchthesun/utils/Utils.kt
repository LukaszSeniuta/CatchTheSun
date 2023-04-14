package com.example.catchthesun.utils

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import com.example.catchthesun.R
import com.example.catchthesun.weather_model.Weather
import java.text.SimpleDateFormat

@SuppressLint("SimpleDateFormat")
fun formatDate(timestamp: Int): String {
    val sdf = SimpleDateFormat("EEE, MMM d")
    val date = java.util.Date(timestamp.toLong() * 1000)

    return sdf.format(date)
}

@SuppressLint("SimpleDateFormat")
fun formatDateDay(timestamp: Int): String {
    val sdf = SimpleDateFormat("EEE")
    val date = java.util.Date(timestamp.toLong() * 1000)

    return sdf.format(date)
}

@SuppressLint("SimpleDateFormat")
fun formatDateTime(timestamp: Int): String {
    val sdf = SimpleDateFormat("HH")
    val date = java.util.Date(timestamp.toLong() * 1000)

    return sdf.format(date)
}
@SuppressLint("SimpleDateFormat")
fun formatDateTimeMin(timestamp: Int): String {
    val sdf = SimpleDateFormat("HH:mm")
    val date = java.util.Date(timestamp.toLong() * 1000)

    return sdf.format(date)
}

fun myToast(context: Context){
    Toast.makeText(context, "This is a Sample Toast", Toast.LENGTH_LONG).show()
}


fun formatDecimals(item: Double): String {
    val myItem: Double
    return if(item > -0.50 && item <= -0.01){
        myItem = item * -1
        " %.0f".format(myItem)
    }else {
        " %.0f".format(item)
    }
}


fun findLowest(weather: Weather): Double{
    val minTempList = mutableListOf<Double>()
    for (day in weather.daily - weather.daily[0]){
        minTempList.add(day.temp.min)
    }
    return minTempList.minOf { Double -> Double }
}


fun findHighest(weather: Weather): Double{
    val maxTempList = mutableListOf<Double>()
    for (day in weather.daily - weather.daily[0]){
        maxTempList.add(day.temp.max)
    }
    return maxTempList.maxOf { Double -> Double }
}

@Composable
fun whichWeatherIcon(icon: String): Int {

    return when (icon) {
        "01d" -> R.drawable.sun_icon
        "01n" -> R.drawable.moon_night
        "02d" -> R.drawable.clear_sky_icon
        "02n" -> R.drawable.moon_cloud_night
        "03d" -> R.drawable.cloud_icon
        "03n" -> R.drawable.cloud_icon
        "04d" -> R.drawable.broken_clouds_icon
        "04n" -> R.drawable.broken_clouds_icon
        "09d" -> R.drawable.rain_broken_clouds_icon
        "09n" -> R.drawable.rain_broken_clouds_icon
        "10d" -> R.drawable.rain_icon
        "10n" -> R.drawable.rain_icon
        "11d" -> R.drawable.thunder_icon
        "11n" -> R.drawable.thunder_icon
        "13d" -> R.drawable.snow_icon
        "13n" -> R.drawable.snow_icon
        "50d" -> R.drawable.mist_icon
        "50n" -> R.drawable.mist_icon
        else -> R.drawable.clear_sky_icon
    }

}
@Composable
fun whichVideoPlay(icon: String): String{

    return when (icon) {
        "01d" -> "2131820549"
        "01n" -> "2131820549"
        "02d" -> "2131820548"
        "02n" -> "2131820548"
        "03d" -> "2131820544"
        "03n" -> "2131820544"
        "04d" -> "2131820544"
        "04n" -> "2131820544"
        "09d" -> "2131820546"
        "09n" -> "2131820546"
        "10d" -> "2131820546"
        "10n" -> "2131820546"
        "11d" -> "2131820550"
        "11n" -> "2131820550"
        "13d" -> "2131820547"
        "13n" -> "2131820547"
        "50d" -> "2131820545"
        "50n" -> "2131820545"
        else -> "2131820547"
    }
}

