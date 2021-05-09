package com.example.notflix.data.remote

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.notflix.data.local.entity.EpisodesEntity
import com.example.notflix.data.local.entity.MoviesEntity
import com.example.notflix.data.local.entity.TvShowEntity
import com.example.notflix.values.ResourceData

interface NotflixDataSource {

    fun getAllTrendingMovies() : LiveData<ResourceData<PagedList<MoviesEntity>>>

    fun getAllPopularTvShow() : LiveData<ResourceData<PagedList<TvShowEntity>>>

    fun getDetailMovie(movie_id : Int) : LiveData<ResourceData<MoviesEntity>>

    fun getDetailTv(tv_id : Int) : LiveData<ResourceData<TvShowEntity>>

    fun getEpisodes() : LiveData<List<EpisodesEntity>>




}