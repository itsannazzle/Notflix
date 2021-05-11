package com.example.notflix.ui.favorite

import androidx.lifecycle.ViewModel
import com.example.notflix.data.remote.MoviesRepositories

class FavoriteViewModel(private val moviesRepositories: MoviesRepositories) : ViewModel() {

    private val isFavorited = false


    fun checkFavorite(movieId : Int) : Boolean{
        return moviesRepositories.checkFavorite(movieId)
    }
}