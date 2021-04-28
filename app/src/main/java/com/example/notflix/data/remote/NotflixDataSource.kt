package com.example.notflix.data.remote

import androidx.lifecycle.LiveData
import com.example.notflix.entity.EpisodesEntity
import com.example.notflix.entity.MoviesEntity
import com.example.notflix.entity.TvShowEntity

interface NotflixDataSource {

    fun getAllTrendingMovies() : LiveData<List<MoviesEntity>>

    fun getAllPopularTvShow() : LiveData<List<TvShowEntity>>

    fun getDetailMovie(movie_id : Int) : LiveData<MoviesEntity>

    fun getDetailTv(tv_id : Int) : LiveData<TvShowEntity>

    fun getEpisodes() : LiveData<List<EpisodesEntity>>

}