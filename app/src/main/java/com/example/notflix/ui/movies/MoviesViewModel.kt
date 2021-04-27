package com.example.notflix.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.notflix.data.remote.MoviesRepositories
import com.example.notflix.entity.MoviesEntity

class MoviesViewModel(private val moviesRepositories: MoviesRepositories) : ViewModel() {

    //fun getMovies() : List<MoviesEntity> = DataMovies.generateDataMovies()

    fun showTrendingMovies() : LiveData<List<MoviesEntity>> = moviesRepositories.getAllTrendingMovies()

}