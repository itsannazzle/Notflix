package com.example.notflix.ui.tvshow

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.nextint.core.domain.usecase.NotflixUsecase

class TvShowViewModel(private val notflixUsecase: NotflixUsecase) : ViewModel() {

    fun showTvShow() = LiveDataReactiveStreams.fromPublisher(notflixUsecase.getAllPopularTvShow())

}