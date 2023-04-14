package com.example.catchthesun.screen.search

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.catchthesun.R
import com.example.catchthesun.components.CatchTheSunAppBottomBar
import com.example.catchthesun.components.SearchBar
import com.example.catchthesun.navigation.WeatherScreens
import com.example.catchthesun.screen.main.ShowVideo
import com.example.catchthesun.ui.theme.myLightPurple
import com.example.catchthesun.utils.whichVideoPlay

@Composable
fun SearchScreen(
    navController: NavController,
    icon: String = "53d"
) {

    Log.d("SEARCH SCREEEEEEN", "AAAAAAAAAAAAAAAAAAAAAAAAAAAAA")

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(color = myLightPurple)
    ) {


        Scaffold(bottomBar = {
            CatchTheSunAppBottomBar(
                onFavouritesButtonClicked = { navController.navigate(WeatherScreens.FavouritesScreen.route) },
                onSearchButtonClicked = { navController.navigate(WeatherScreens.SearchScreen.route) })
        }) { innerPadding ->
            Box(
                modifier = Modifier.padding(
                    PaddingValues(0.dp, 0.dp, 0.dp, innerPadding.calculateBottomPadding())
                )
            ) {

                ShowVideo(id = whichVideoPlay(icon = icon))

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Black.copy(alpha = 0.3f)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 5.dp, top = 20.dp, end = 10.dp)
                    ) {

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 20.dp),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            IconButton(onClick = { navController.popBackStack() }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.back_arrow),
                                    contentDescription = null,
                                    modifier = Modifier.size(30.dp),
                                    tint = Color.White
                                )
                            }


                            SearchBar {
                                navController.navigate(WeatherScreens.MainScreen.route + "/$it")
                            }


                        }
                    }
                }

            }
        }
    }
}





