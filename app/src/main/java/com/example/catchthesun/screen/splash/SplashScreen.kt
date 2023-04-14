package com.example.catchthesun.screen.splash

import android.util.Log
import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.catchthesun.R
import com.example.catchthesun.navigation.WeatherScreens
import com.example.catchthesun.ui.theme.myDarkPurple
import com.example.catchthesun.ui.theme.myLightPurple
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {

    val scale = remember {
        Animatable(0f)
    }

    Log.d("SPLASH SCREEEEEEEN","AAAAAAAAAAAAAAAAAA")

    Surface(color = myLightPurple) {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            LaunchedEffect(key1 = true, block = {
                scale.animateTo(
                    targetValue = 0.9f,
                    animationSpec = tween(
                        durationMillis = 1000,
                        easing = {
                            OvershootInterpolator(8f)
                                .getInterpolation(it)
                        })
                )
                delay(2500L)
                navController.navigate("${WeatherScreens.MainScreen.route}/{katowice}")
            })

            Surface(
                modifier = Modifier
                    .padding(15.dp)
                    .size(330.dp)
                    .scale(scale.value),
                shape = CircleShape,
                color = myLightPurple,
                border = BorderStroke(2.dp, color = myDarkPurple)
            ) {
                Column(
                    modifier = Modifier.padding(1.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.clear_sky_icon),
                        contentDescription = "sunny cloud icon",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.size(95.dp)
                    )
                    Text(
                        text = "Catch The Sun!", style = MaterialTheme.typography.h5,
                        color = myDarkPurple
                    )

                }
            }
        }
    }

}