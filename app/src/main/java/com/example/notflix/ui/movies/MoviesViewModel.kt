package com.example.notflix.ui.movies

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.nextint.core.domain.usecase.NotflixUsecase

class MoviesViewModel(private val notflixUsecase: NotflixUsecase) : ViewModel() {

    fun showTrendingMovies() = LiveDataReactiveStreams.fromPublisher(notflixUsecase.getAllTrendingMovies())


}