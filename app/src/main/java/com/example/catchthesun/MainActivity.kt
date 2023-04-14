@file:OptIn(ExperimentalAnimationApi::class)

package com.example.catchthesun

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.catchthesun.navigation.WeatherNavigation
import com.example.catchthesun.navigation.addMainScreen
import com.example.catchthesun.navigation.addSearchScreen
import com.example.catchthesun.navigation.WeatherScreens
import com.example.catchthesun.screen.main.buildExoPlayer
import com.example.catchthesun.screen.main.buildPlayerView
import com.example.catchthesun.ui.theme.CatchTheSunTheme
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)
        windowInsetsController.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        windowInsetsController.hide(WindowInsetsCompat.Type.navigationBars())
        setContent {
            CatchTheSunTheme {

                WeatherNavigation()

            }
        }
    }

//    @SuppressLint("DiscouragedApi")
//    @Composable
//    fun getVideoUri(videoName: String): Uri {
//        val rawId = resources.getIdentifier(videoName, "raw", packageName)
//        val videoUri = "android.resource://$packageName/$rawId"
//        Log.d("WAAAAAAAAAAZNE!!!!!!!!!", videoUri)
//        //val videoUri = "android.resource://com.example.caththesun/2131820546"
//        return Uri.parse(videoUri)
//    }
//
//    @SuppressLint("RememberReturnType")
//    @Composable
//    fun ShowVideo(videoUri: Uri) {
//
//        //val videoUri = "android.resource://com.example.caththesun/$id"
//        val context = LocalContext.current
//
//        val exoPlayer = remember { context.buildExoPlayer(videoUri) }
//
//
//        DisposableEffect(
//            AndroidView(
//                factory = { it.buildPlayerView(exoPlayer) },
//                modifier = Modifier.fillMaxSize()
//            )
//        ) {
//            onDispose {
//                exoPlayer.release()
//            }
//
//        }
//    }


}

// clouds movie = android.resource://com.example.catchthesun/2131820544
// raindrops movie = android.resource://com.example.catchthesun/2131820545
// sun_clouds movie = android.resource://com.example.catchthesun/2131820546
// sun movie = android.resource://com.example.catchthesun/2131820547
// thunder = android.resource://com.example.catchthesun/2131820548


//@ExperimentalComposeUiApi
//@ExperimentalAnimationApi
//fun NavGraphBuilder.addHeroList(
//    navController: NavController,
//    imageLoader: ImageLoader,
//    width: Int,
//) {
//    composable(
//        route = Screen.HeroList.route,
//        exitTransition = {_, _ ->
//            slideOutHorizontally(
//                targetOffsetX = { -width },
//                animationSpec = tween(
//                    durationMillis = 300,
//                    easing = FastOutSlowInEasing
//                )
//            ) + fadeOut(animationSpec = tween(300))
//        },
//        popEnterTransition = { initial, _ ->
//            slideInHorizontally(
//                initialOffsetX = { -width },
//                animationSpec = tween(
//                    durationMillis = 300,
//                    easing = FastOutSlowInEasing
//                )
//            ) + fadeIn(animationSpec = tween(300))
//        },
//    ){
//        val viewModel: HeroListViewModel = hiltViewModel()
//        HeroList(
//            state = viewModel.state.value,
//            events = viewModel::onTriggerEvent,
//            navigateToDetailScreen = { heroId ->
//                navController.navigate("${Screen.HeroDetail.route}/$heroId")
//            },
//            imageLoader = imageLoader,
//        )
//    }
//}
//
//@ExperimentalAnimationApi
//fun NavGraphBuilder.addHeroDetail(
//    imageLoader: ImageLoader,
//    width: Int,
//) {
//    composable(
//        route = Screen.HeroDetail.route + "/{heroId}",
//        arguments = Screen.HeroDetail.arguments,
//        enterTransition = { _, _ ->
//            slideInHorizontally(
//                initialOffsetX = { width },
//                animationSpec = tween(
//                    durationMillis = 300,
//                    easing = FastOutSlowInEasing
//                )
//            ) + fadeIn(animationSpec = tween(300))
//        },
//        popExitTransition = { _, target ->
//            slideOutHorizontally(
//                targetOffsetX = { width },
//                animationSpec = tween(
//                    durationMillis = 300,
//                    easing = FastOutSlowInEasing
//                )
//            ) + fadeOut(animationSpec = tween(300))
//        }
//    ){
//        val viewModel: HeroDetailViewModel = hiltViewModel()
//        HeroDetail(
//            state = viewModel.state.value,
//            events = viewModel::onTriggerEvent,
//            imageLoader = imageLoader
//        )
//    }
//}



