package com.example.notflix.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.notflix.data.remote.MoviesRepositories
import com.example.notflix.data.local.entity.EpisodesEntity
import com.example.notflix.data.local.entity.MoviesEntity
import com.example.notflix.data.local.entity.TvShowEntity
import com.example.notflix.values.ResourceData

class DetailMoviesViewModel(private val moviesRepositories: MoviesRepositories) : ViewModel() {

    private var movieId : Int = 0
    private var tvshowId : Int = 0
    private var state = false

    private val moviesEntity = MoviesEntity()


    fun getSelectedMovie(movieId : Int){
        this.movieId = movieId
    }

    fun getSelectedTvShow(tvshowId : Int){
        this.tvshowId = tvshowId
    }

    fun showDetailMovie() : LiveData<ResourceData<MoviesEntity>> = moviesRepositories.getDetailMovie(movieId)

    fun showDetailTvShow() : LiveData<ResourceData<TvShowEntity>> = moviesRepositories.getDetailTv(tvshowId)

    //fun showEpisodes() : LiveData<ResourceData<PagedList<EpisodesEntity>>> = moviesRepositories.getEpisodes()

    fun showTrendingMovies() : LiveData<ResourceData<PagedList<MoviesEntity>>> = moviesRepositories.getAllTrendingMovies()

    fun isFavorite(heartState : Boolean){
        if (heartState){
            moviesRepositories.insertFavMovie(moviesEntity)
        }
    }

}