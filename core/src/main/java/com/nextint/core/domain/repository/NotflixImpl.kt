package com.nextint.core.domain.repository

import androidx.paging.PagedList
import com.nextint.core.domain.model.MoviesModel
import com.nextint.core.domain.model.TvShowModel
import com.nextint.core.values.ResourceData
import io.reactivex.Flowable

interface NotflixImpl {
    fun getAllTrendingMovies() : Flowable<ResourceData<PagedList<MoviesModel>>>

    fun getAllPopularTvShow() : Flowable<ResourceData<PagedList<TvShowModel>>>

    fun getDetailMovie(movie_id : Int) : Flowable<ResourceData<MoviesModel>>

    fun getDetailTv(tv_id : Int) : Flowable<ResourceData<TvShowModel>>

    fun getAllFavMovie() : Flowable<PagedList<MoviesModel>>

    fun getAllFavTv() : Flowable<PagedList<TvShowModel>>

    fun insertFavMovie(movie: MoviesModel, isFavorite : Boolean)

    fun insertFavTv(tv: TvShowModel, isFavorite: Boolean)
}