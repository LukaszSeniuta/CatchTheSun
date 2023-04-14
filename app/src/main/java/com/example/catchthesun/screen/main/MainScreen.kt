package com.example.catchthesun.screen.main

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.catchthesun.R
import com.example.catchthesun.city_model.City
import com.example.catchthesun.components.CatchTheSunAppBottomBar
import com.example.catchthesun.data.DataOrException
import com.example.catchthesun.navigation.WeatherScreens
import com.example.catchthesun.ui.theme.myDarkPurple
import com.example.catchthesun.ui.theme.myLightPurple
import com.example.catchthesun.utils.formatDateTimeMin
import com.example.catchthesun.utils.formatDecimals
import com.example.catchthesun.utils.whichVideoPlay
import com.example.catchthesun.weather_model.Weather
import com.example.catchthesun.widgets.*
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.ui.StyledPlayerView

@Composable

fun MainScreen(
    navController: NavController,
    city: String,
    mainViewModel: MainViewModel = hiltViewModel(),
) {
    Log.d("MAIN SCREEEEN", "AAAAAAAAAAAAAAAAAAAAAAAAAAA")

    val mContext = LocalContext.current

    var city = city

    val cityData = produceState<DataOrException<City, Boolean, Exception>>(
        initialValue = DataOrException(loading = true)
    ) {
        value = mainViewModel.getCityData(city)
    }.value

    Log.d("CITY DATAAAA", cityData.loading.toString())

    if (cityData.loading == true) {


        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = myLightPurple),
            horizontalAlignment = CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            CircularProgressIndicator(color = myDarkPurple)

        }
    } else if (cityData.data!!.isNotEmpty()) {


        val weatherData = produceState<DataOrException<Weather, Boolean, Exception>>(
            initialValue = DataOrException(loading = true)
        ) {
            value = mainViewModel.getWeatherData(
                lat = cityData.data!![0].lat,
                lon = cityData.data!![0].lon
            )
        }.value

        if (weatherData.loading == true) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = myLightPurple),
                horizontalAlignment = CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                CircularProgressIndicator(color = myDarkPurple)

            }
        } else if (weatherData.data != null) {

            MainScaffold(
                weather = weatherData.data!!,
                city = cityData.data!!,
                navController
            )
        }



    }
}


@Composable
fun MainScaffold(
    weather: Weather,
    city: City,
    navController: NavController
) {


    Scaffold(bottomBar = {
        CatchTheSunAppBottomBar(
            onFavouritesButtonClicked = {
                navController.navigate("${WeatherScreens.FavouritesScreen.route}/${weather.current.weather[0].icon}")
            },
            onSearchButtonClicked = {
                navController.navigate("${WeatherScreens.SearchScreen.route}/${weather.current.weather[0].icon}")
            })
    }) { innerPadding ->
        Box(
            modifier = Modifier.padding(
                PaddingValues(0.dp, 0.dp, 0.dp, innerPadding.calculateBottomPadding())
            )
        ) {
            MainContent(weather = weather, city = city)
        }
    }

}

@Composable
fun MainContent(weather: Weather, city: City) {

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(color = myLightPurple)
    ) {

        ShowVideo(id = whichVideoPlay(icon = weather.current.weather[0].icon))


        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.Black.copy(alpha = 0.3f)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {


                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 30.dp, start = 20.dp, end = 20.dp, bottom = 20.dp)
                        .background(color = Color.Transparent),
                    horizontalAlignment = CenterHorizontally
                ) {
                    WeatherTopInfo(weather = weather, city = city)
                }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 10.dp, start = 20.dp, end = 20.dp, bottom = 10.dp)
                        .background(color = Color.Transparent),
                    horizontalAlignment = CenterHorizontally
                ) {

                    HourlyWeatherCard(weather = weather)


                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 10.dp, start = 20.dp, end = 20.dp, bottom = 10.dp)
                        .background(color = Color.Transparent),
                    horizontalAlignment = CenterHorizontally
                ) {

                    WeeklyForecastCard(weather = weather)
                }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 10.dp, start = 20.dp, end = 20.dp, bottom = 10.dp)
                        .background(color = Color.Transparent),
                    horizontalAlignment = CenterHorizontally
                ) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        SquareInfoCard(
                            upperText = "Odczuwalna",
                            mainText = formatDecimals(weather.current.feels_like) + "°",
                            icon = R.drawable.celsius_bar_icon
                        )

                        SquareInfoCard(
                            upperText = "Wschód Słońca",
                            mainText = formatDateTimeMin(weather.current.sunrise),
                            icon = R.drawable.sunrise_icon
                        )


                    }

                }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 10.dp, start = 20.dp, end = 20.dp, bottom = 10.dp)
                        .background(color = Color.Transparent),
                    horizontalAlignment = CenterHorizontally
                ) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {


                        SquareInfoCard(
                            padding = 25.dp,
                            upperText = "Ciśnienie",
                            mainText = weather.current.pressure.toString(),
                            optionalText = "hPa",
                            icon = R.drawable.barometer
                        )

                        SquareInfoCard(
                            upperText = "Wilgotność",
                            mainText = weather.current.humidity.toString() + "%",
                            icon = R.drawable.humidity
                        )


                    }

                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 10.dp, start = 20.dp, end = 20.dp, bottom = 10.dp)
                        .background(color = Color.Transparent),
                    horizontalAlignment = CenterHorizontally
                ) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        SquareWindInfoCard(
                            icon = R.drawable.wind,
                            mainIcon = R.drawable.compass_arrow_icon,
                            upperText = "Wiatr",
                            degrees = weather.current.wind_deg.toFloat(),
                            mainText = formatDecimals(weather.current.wind_speed) + "m/s"
                        )

                        SquareInfoCard(
                            upperText = "Indeks UV",
                            mainText = formatDecimals(weather.current.uvi) + " ",
                            icon = R.drawable.uv_icon
                        )


                    }
                }
            }
        }
    }

}


fun Context.buildExoPlayer(uri: Uri) =
    ExoPlayer.Builder(this).build().apply {
        setMediaItem(MediaItem.fromUri(uri))
        repeatMode = Player.REPEAT_MODE_ALL
        playWhenReady = true
        prepare()
    }

fun Context.buildPlayerView(exoPlayer: ExoPlayer) =
    StyledPlayerView(this).apply {
        player = exoPlayer
        layoutParams = FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        useController = false
        resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
    }


@SuppressLint("RememberReturnType")
@Composable
fun ShowVideo(id: String) {

    val videoUri = "android.resource://com.example.caththesun/$id"
    val context = LocalContext.current

    val exoPlayer = remember { context.buildExoPlayer(Uri.parse(videoUri)) }


    DisposableEffect(
        AndroidView(
            factory = { it.buildPlayerView(exoPlayer) },
            modifier = Modifier.fillMaxSize()
        )
    ) {
        onDispose {
            exoPlayer.release()
        }

    }
}


// clouds movie = android.resource://com.example.catchthesun/2131820544
// raindrops movie = android.resource://com.example.catchthesun/2131820545
// sun_clouds movie = android.resource://com.example.catchthesun/2131820546
// sun movie = android.resource://com.example.catchthesun/2131820547
// thunder = android.resource://com.example.catchthesun/2131820548

