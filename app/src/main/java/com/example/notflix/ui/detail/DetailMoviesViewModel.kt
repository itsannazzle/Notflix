package com.example.notflix.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.notflix.data.remote.MoviesRepositories
import com.example.notflix.data.local.entity.EpisodesEntity
import com.example.notflix.data.local.entity.MoviesEntity
import com.example.notflix.data.local.entity.TvShowEntity
import com.example.notflix.values.ResourceData

class DetailMoviesViewModel(private val moviesRepositories: MoviesRepositories) : ViewModel() {

    private var movieId = MutableLiveData<Int>()
    private var tvshowId = MutableLiveData<Int>()
    private var state = false

    private val moviesEntity = MoviesEntity()


    fun getSelectedMovie(movieId : Int){
        this.movieId.value = movieId
    }

    fun getSelectedTvShow(tvshowId : Int){
        this.tvshowId.value = tvshowId
    }

    fun showDetailMovie() : LiveData<ResourceData<MoviesEntity>> = Transformations.switchMap(movieId){
        mDetailMovie -> moviesRepositories.getDetailMovie(mDetailMovie)
    }

    fun showDetailTvShow() : LiveData<ResourceData<TvShowEntity>> = Transformations.switchMap(tvshowId){
      mTvShowId -> moviesRepositories.getDetailTv(mTvShowId)
    }

    //fun showEpisodes() : LiveData<ResourceData<PagedList<EpisodesEntity>>> = moviesRepositories.getEpisodes()

    fun showTrendingMovies() : LiveData<ResourceData<PagedList<MoviesEntity>>> = moviesRepositories.getAllTrendingMovies()

    fun isFavorite(heartState : Boolean){
        if (heartState){
            moviesRepositories.insertFavMovie(moviesEntity)
        }
    }

}