package com.example.catchthesun.screen.favourites

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.catchthesun.screen.main.MainViewModel
//import com.example.catchthesun.screen.main.ShowVideo
import com.example.catchthesun.utils.whichVideoPlay

@Composable
fun FavouritesScreen(
    navController: NavController,
    mainViewModel: MainViewModel = hiltViewModel(),
    icon: String
){

    //ShowVideo(id = whichVideoPlay(icon = icon))

}


