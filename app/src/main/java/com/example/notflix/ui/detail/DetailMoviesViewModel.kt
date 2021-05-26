package com.example.notflix.ui.detail

import androidx.lifecycle.*
import com.nextint.core.domain.usecase.NotflixUsecase

class DetailMoviesViewModel(private val notflixUsecase: NotflixUsecase) : ViewModel() {

    private var movieId : Int = 0
    private var tvshowId : Int = 0


    fun getSelectedMovie(movieId : Int){
        this.movieId = movieId
    }

    fun getSelectedTvShow(tvshowId : Int){
        this.tvshowId = tvshowId
    }

    var detailMovie = LiveDataReactiveStreams.fromPublisher(notflixUsecase.getDetailMovie(movieId))



    var detailTvShow = LiveDataReactiveStreams.fromPublisher(notflixUsecase.getDetailTv(tvshowId))

    fun showEpisodes() = LiveDataReactiveStreams.fromPublisher(notflixUsecase.getEpisodes())

    fun showTrendingMovies() = LiveDataReactiveStreams.fromPublisher(notflixUsecase.getAllTrendingMovies())

    fun isFavoriteMovie(){
        val isFav =detailMovie.value
        if (isFav != null){
            val favorite =isFav.data
            if (favorite != null){
                val moviesEntity = isFav.data
                val favState = !favorite.favorite
                if (moviesEntity != null) {
                    notflixUsecase.insertFavMovie(moviesEntity,favState)
                }
            }
        }
    }

    fun isFavoriteTv(){
        val isFav = detailTvShow.value
        if (isFav != null){
            val favorite =isFav.data
            if (favorite != null){
                val tvShowEntity = isFav.data
                val favState = !favorite.favorite
                if (tvShowEntity != null) {
                    notflixUsecase.insertFavTv(tvShowEntity,favState)
                }
            }
        }
    }
}