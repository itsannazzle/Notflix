package com.example.notflix.ui.detail

import androidx.lifecycle.*
import androidx.paging.PagedList
import com.example.notflix.data.remote.MoviesRepositories
import com.example.notflix.data.local.entity.EpisodesEntity
import com.example.notflix.data.local.entity.MoviesEntity
import com.example.notflix.data.local.entity.TvShowEntity
import com.example.notflix.values.ResourceData
import kotlinx.coroutines.launch

class DetailMoviesViewModel(private val moviesRepositories: MoviesRepositories) : ViewModel() {

    private var movieId = MutableLiveData<Int>()
    private var tvshowId = MutableLiveData<Int>()


    fun getSelectedMovie(movieId : Int){
        this.movieId.value = movieId
    }

    fun getSelectedTvShow(tvshowId : Int){
        this.tvshowId.value = tvshowId
    }

    var detailMovie : LiveData<ResourceData<MoviesEntity>> = Transformations.switchMap(movieId){
        mDetailMovie -> moviesRepositories.getDetailMovie(mDetailMovie)
    }

    var detailTvShow : LiveData<ResourceData<TvShowEntity>> = Transformations.switchMap(tvshowId){
      mTvShowId -> moviesRepositories.getDetailTv(mTvShowId)
    }

    //fun showEpisodes() : LiveData<ResourceData<PagedList<EpisodesEntity>>> = moviesRepositories.getEpisodes()

    fun showTrendingMovies() : LiveData<ResourceData<PagedList<MoviesEntity>>> = moviesRepositories.getAllTrendingMovies()

    fun isFavoriteMovie(){
        val isFav =detailMovie.value
        if (isFav != null){
            val favorite =isFav.data
            if (favorite != null){
                val moviesEntity = isFav.data
                val favState = !favorite.favorite
                moviesRepositories.insertFavMovie(moviesEntity,favState)
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
                moviesRepositories.insertFavTv(tvShowEntity,favState)
            }
        }
    }
}