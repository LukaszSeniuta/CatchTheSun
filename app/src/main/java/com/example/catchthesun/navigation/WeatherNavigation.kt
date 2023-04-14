@file:OptIn(ExperimentalAnimationApi::class)

package com.example.catchthesun.navigation

import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.example.catchthesun.screen.favourites.FavouritesScreen
import com.example.catchthesun.screen.main.MainScreen
import com.example.catchthesun.screen.main.MainViewModel
import com.example.catchthesun.screen.search.SearchScreen
import com.example.catchthesun.screen.splash.SplashScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun WeatherNavigation() {
    val navController = rememberAnimatedNavController()
    AnimatedNavHost(
        navController = navController,
        startDestination = WeatherScreens.SplashScreen.route,
        builder = {

            composable(WeatherScreens.SplashScreen.route) {
                SplashScreen(navController = navController)
            }

            addMainScreen(navController = navController)

            addSearchScreen(navController = navController)

            addFavouritesScreen(navController = navController)


        })

}


fun NavGraphBuilder.addMainScreen(navController: NavController) {
    composable(
        route = "${WeatherScreens.MainScreen.route}/{city}",
        exitTransition = {
            slideOutHorizontally(
                targetOffsetX = { -300 },
                animationSpec = tween(
                    durationMillis = 300,
                    easing = FastOutSlowInEasing
                )
            ) + fadeOut(animationSpec = tween(300))
        },
        popEnterTransition = {
            slideInHorizontally(
                initialOffsetX = { -300 },
                animationSpec = tween(
                    durationMillis = 300,
                    easing = FastOutSlowInEasing
                )
            ) + fadeIn(animationSpec = tween(300))
        }
    ) {navBack ->
        MainScreen(
            navController = navController,
            city = navBack.arguments!!.getString("city")!!
        )
    }
}


fun NavGraphBuilder.addSearchScreen(navController: NavController) {
    composable(
        route = "${WeatherScreens.SearchScreen.route}/{icon}",
        enterTransition = {
            slideInHorizontally(
                initialOffsetX = { 300 },
                animationSpec = tween(
                    durationMillis = 300,
                    easing = FastOutSlowInEasing
                )
            ) + fadeIn(animationSpec = tween(300))
        },
        popExitTransition = {
            slideOutHorizontally(
                targetOffsetX = { 300 },
                animationSpec = tween(
                    durationMillis = 300,
                    easing = FastOutSlowInEasing
                )
            ) + fadeOut(animationSpec = tween(300))
        }
    ) { navBack ->
        SearchScreen(
            navController = navController,
            icon = navBack.arguments!!.getString("icon")!!
        )

    }
}
fun NavGraphBuilder.addFavouritesScreen(navController: NavController) {
    composable(
        route = "${WeatherScreens.FavouritesScreen.route}/{icon}",
        enterTransition = {
            slideInHorizontally(
                initialOffsetX = { 300 },
                animationSpec = tween(
                    durationMillis = 300,
                    easing = FastOutSlowInEasing
                )
            ) + fadeIn(animationSpec = tween(300))
        },
        popExitTransition = {
            slideOutHorizontally(
                targetOffsetX = { 300 },
                animationSpec = tween(
                    durationMillis = 300,
                    easing = FastOutSlowInEasing
                )
            ) + fadeOut(animationSpec = tween(300))
        }
    ) { navBack ->
        FavouritesScreen(
            navController = navController,
            icon = navBack.arguments!!.getString("icon")!!
        )

    }
}