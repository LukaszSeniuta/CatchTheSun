package com.example.catchthesun.pollution_model

data class Daily(
    val o3: List<O3>,
    val pm10: List<Pm10>,
    val pm25: List<Pm10>
)