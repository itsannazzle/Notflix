package com.example.notflix.data.remote

import androidx.lifecycle.LiveData
import com.example.notflix.entity.MoviesEntity
import com.example.notflix.entity.PrevMoviesEntity
import com.example.notflix.entity.PrevTVEntity
import com.example.notflix.entity.TvShowEntity

interface NotflixDataSource {

    fun getAllTrendingMovies() : LiveData<List<PrevMoviesEntity>>

    fun getAllPopularTvShow() : LiveData<List<PrevTVEntity>>

    fun getDetailMovie(movie_id : Int) : LiveData<MoviesEntity>

    fun getDetailTv(tv_id : Int) : LiveData<TvShowEntity>

}