package com.example.notflix.ui.movies

import androidx.lifecycle.ViewModel
import com.example.notflix.entity.MoviesEntity
import com.example.notflix.utils.DataMovies

class MoviesViewModel : ViewModel() {

    fun getMovies() : List<MoviesEntity> = DataMovies.generateDataMovies()

}