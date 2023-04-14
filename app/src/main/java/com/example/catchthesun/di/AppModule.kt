package com.example.catchthesun.di

import android.content.Context
import androidx.room.Room
import com.example.catchthesun.data.CatchTheSunDao
import com.example.catchthesun.data.CatchTheSunDatabase
import com.example.catchthesun.network.CityApi
import com.example.catchthesun.network.PollutionApi
import com.example.catchthesun.network.WeatherApi
import com.example.catchthesun.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideCatchTheSunDao(catchTheSunDatabase: CatchTheSunDatabase): CatchTheSunDao
    = catchTheSunDatabase.catchTheSunDao()

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): CatchTheSunDatabase
    = Room.databaseBuilder(
        context,
        CatchTheSunDatabase::class.java,
        "catch_the_sun_database")
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideOpenWeatherApi(): WeatherApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }

    @Provides
    @Singleton
    fun provideOpenCityApi(): CityApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CityApi::class.java)
    }

    @Provides
    @Singleton
    fun provideOpenPollutionApi(): PollutionApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_POLLUTION_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PollutionApi::class.java)
    }
}