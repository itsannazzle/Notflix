package com.example.notflix.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.notflix.data.remote.MoviesRepositories
import com.example.notflix.data.local.entity.MoviesEntity
import com.example.notflix.values.ResourceData

class MoviesViewModel(private val moviesRepositories: MoviesRepositories) : ViewModel() {

    //fun getMovies() : List<MoviesEntity> = DataMovies.generateDataMovies()

    fun showTrendingMovies() : LiveData<ResourceData<PagedList<MoviesEntity>>> = moviesRepositories.getAllTrendingMovies()

}