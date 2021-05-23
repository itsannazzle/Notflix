package com.example.notflix.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.notflix.core.data.MoviesRepositories
import com.example.notflix.core.data.local.entity.MoviesEntity
import com.example.notflix.core.domain.model.MoviesModel
import com.example.notflix.core.domain.usecase.NotflixUsecase
import com.example.notflix.values.ResourceData

class MoviesViewModel(private val notflixUsecase: NotflixUsecase) : ViewModel() {

   // fun showTrendingMovies() : LiveData<ResourceData<PagedList<MoviesEntity>>> = moviesRepositories.getAllTrendingMovies()
    fun showTrendingMovies() = LiveDataReactiveStreams.fromPublisher(notflixUsecase.getAllTrendingMovies())

}