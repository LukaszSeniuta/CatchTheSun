package com.example.catchthesun.screen.favourites

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catchthesun.repository.CatchTheSunDbRepository
import com.example.catchthesun.weather_model.Favourite
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel @Inject constructor(private val repository: CatchTheSunDbRepository): ViewModel() {

    private val _favList = MutableStateFlow<List<Favourite>>(emptyList())

    val favList = _favList.asStateFlow()

    init{

        viewModelScope.launch(Dispatchers.IO) {
            repository.getFavourites().distinctUntilChanged()
                .collect{ listOfFav ->
                    if (listOfFav.isNullOrEmpty()){
                        Log.d("TAG", ": Empty favs")
                    }else{
                        _favList.value = listOfFav
                        Log.d("FAV", ":${listOfFav}")

                    }
                }
        }

    }

    fun insertFavourite(favourite: Favourite) = viewModelScope.launch { repository.insertFavourite(favourite) }

    fun deleteAllFavourites() = viewModelScope.launch { repository.deleteAllFavourites() }

    fun deleteFavourite(favourite: Favourite) = viewModelScope.launch { repository.deleteFavourite(favourite) }
}