package com.example.catchthesun.navigation

sealed class WeatherScreens(val route: String){

    object SplashScreen: WeatherScreens(ConstantWeatherScreenName.SPLASH_SCREEN)
    object MainScreen: WeatherScreens(ConstantWeatherScreenName.MAIN_SCREEN)
    object SearchScreen: WeatherScreens(ConstantWeatherScreenName.SEARCH_SCREEN)
    object FavouritesScreen: WeatherScreens(ConstantWeatherScreenName.FAVOURITES_SCREEN)
}
