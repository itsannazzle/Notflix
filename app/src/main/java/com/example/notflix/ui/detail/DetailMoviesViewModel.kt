package com.example.notflix.ui.detail

import androidx.lifecycle.*
import androidx.paging.PagedList
import com.example.notflix.core.data.local.entity.EpisodesEntity
import com.example.notflix.core.data.local.entity.MoviesEntity
import com.example.notflix.core.data.local.entity.TvShowEntity
import com.example.notflix.core.data.MoviesRepositories
import com.example.notflix.core.domain.usecase.NotflixUsecase
import com.example.notflix.values.ResourceData

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
                notflixUsecase.insertFavMovie(moviesEntity,favState)
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
                notflixUsecase.insertFavTv(tvShowEntity,favState)
            }
        }
    }
}