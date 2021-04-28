package com.example.notflix.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.notflix.data.remote.MoviesRepositories
import com.example.notflix.entity.EpisodesEntity
import com.example.notflix.entity.MoviesEntity
import com.example.notflix.entity.TvShowEntity

class DetailMoviesViewModel(private val moviesRepositories: MoviesRepositories) : ViewModel() {

    private var movieId : Int = 0
    private var tvshowId : Int = 0


    fun getSelectedMovie(movieId : Int){
        this.movieId = movieId
    }

    fun getSelectedTvShow(tvshowId : Int){
        this.tvshowId = tvshowId
    }

    fun showDetailMovie() : LiveData<MoviesEntity> = moviesRepositories.getDetailMovie(movieId)

    fun showDetailTvShow() : LiveData<TvShowEntity> = moviesRepositories.getDetailTv(tvshowId)

    fun showEpisodes() : LiveData<List<EpisodesEntity>> = moviesRepositories.getEpisodes()

    fun showTrendingMovies() : LiveData<List<MoviesEntity>> = moviesRepositories.getAllTrendingMovies()


}