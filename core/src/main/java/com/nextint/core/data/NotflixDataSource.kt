package com.nextint.core.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.nextint.core.data.local.entity.EpisodesEntity
import com.nextint.core.data.local.entity.MoviesEntity
import com.nextint.core.data.local.entity.TvShowEntity
import com.nextint.core.values.ResourceData

interface NotflixDataSource {

    fun getAllTrendingMovies() : LiveData<ResourceData<PagedList<MoviesEntity>>>

    fun getAllPopularTvShow() : LiveData<ResourceData<PagedList<TvShowEntity>>>

    fun getDetailMovie(movie_id : Int) : LiveData<ResourceData<MoviesEntity>>

    fun getDetailTv(tv_id : Int) : LiveData<ResourceData<TvShowEntity>>

    fun getEpisodes() : LiveData<List<EpisodesEntity>>

    fun getAllFavMovie() : LiveData<PagedList<MoviesEntity>>

    fun getAllFavTv() : LiveData<PagedList<TvShowEntity>>

}