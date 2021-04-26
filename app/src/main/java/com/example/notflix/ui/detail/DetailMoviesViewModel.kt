package com.example.notflix.ui.detail

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notflix.BuildConfig
import com.example.notflix.data.remote.MoviesRepositories
import com.example.notflix.data.remote.config.ApiConfig
import com.example.notflix.data.remote.response.ResultsItem
import com.example.notflix.data.remote.response.TrendingResponse
import com.example.notflix.entity.EpisodesEntity
import com.example.notflix.entity.MoviesEntity
import com.example.notflix.entity.TvShowEntity
import com.example.notflix.utils.DataMovies
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

   // fun showEpisodes() : List<EpisodesEntity> = DataMovies.generateEpisodes()


}