package com.nextint.favoritefeature.favorite

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.nextint.core.domain.usecase.NotflixUsecase

class FavoriteViewModel(private val notflixUsecase: NotflixUsecase) : ViewModel() {
    fun showFavMovie() = LiveDataReactiveStreams.fromPublisher(notflixUsecase.getAllFavMovie())

    fun showFavTv() = LiveDataReactiveStreams.fromPublisher(notflixUsecase.getAllFavTv())
}