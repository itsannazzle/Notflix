package com.example.notflix.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.nextint.core.data.local.entity.EpisodesEntity
import com.nextint.core.domain.model.MoviesModel
import com.nextint.core.domain.model.TvShowModel
import com.nextint.core.domain.usecase.NotflixUsecase

class DetailMoviesViewModel(private val notflixUsecase: NotflixUsecase) : ViewModel() {

    fun getDetailMovie(movieId: Int) = LiveDataReactiveStreams.fromPublisher(notflixUsecase.getDetailMovie(movieId))

    fun getDetailTvShow(tvshowId: Int) = LiveDataReactiveStreams.fromPublisher(notflixUsecase.getDetailTv(tvshowId))

    fun showEpisodes() : LiveData<List<EpisodesEntity>> = notflixUsecase.getEpisodes()

    fun showTrendingMovies() = LiveDataReactiveStreams.fromPublisher(notflixUsecase.getAllTrendingMovies())

    fun isFavoriteMovie(moviesModel: MoviesModel, favState : Boolean){
                    notflixUsecase.insertFavMovie(moviesModel,favState)
            }

    fun isFavoriteTv(tvShowModel: TvShowModel, favState: Boolean){
        notflixUsecase.insertFavTv(tvShowModel,favState)
    }


}