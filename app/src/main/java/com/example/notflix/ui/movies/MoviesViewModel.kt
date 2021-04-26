package com.example.notflix.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.notflix.data.remote.MoviesRepositories
import com.example.notflix.entity.MoviesEntity
import com.example.notflix.entity.PrevMoviesEntity
import com.example.notflix.utils.DataMovies

class MoviesViewModel(private val moviesRepositories: MoviesRepositories) : ViewModel() {

    //fun getMovies() : List<MoviesEntity> = DataMovies.generateDataMovies()

    fun showTrendingMovies() : LiveData<List<PrevMoviesEntity>> = moviesRepositories.getAllTrendingMovies()

}