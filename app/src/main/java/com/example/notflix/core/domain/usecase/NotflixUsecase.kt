package com.example.notflix.core.domain.usecase

import androidx.paging.PagedList
import com.example.notflix.core.data.local.entity.EpisodesEntity
import com.example.notflix.core.data.local.entity.MoviesEntity
import com.example.notflix.core.data.local.entity.TvShowEntity
import com.example.notflix.core.domain.repository.NotflixImpl
import com.example.notflix.values.ResourceData
import io.reactivex.Flowable

interface NotflixUsecase {
    fun getAllTrendingMovies() : Flowable<ResourceData<PagedList<MoviesEntity>>>

    fun getAllPopularTvShow() : Flowable<ResourceData<PagedList<TvShowEntity>>>

    fun getDetailMovie(movie_id : Int) : Flowable<ResourceData<MoviesEntity>>

    fun getDetailTv(tv_id : Int) : Flowable<ResourceData<TvShowEntity>>

    fun getEpisodes() : Flowable<List<EpisodesEntity>>

    fun getAllFavMovie() : Flowable<PagedList<MoviesEntity>>

    fun getAllFavTv() : Flowable<PagedList<TvShowEntity>>
}

class NotflixInteractor(private val notflixImpl: NotflixImpl) : NotflixUsecase{
    override fun getAllTrendingMovies(): Flowable<ResourceData<PagedList<MoviesEntity>>> = notflixImpl.getAllTrendingMovies()

    override fun getAllPopularTvShow(): Flowable<ResourceData<PagedList<TvShowEntity>>> = notflixImpl.getAllPopularTvShow()

    override fun getDetailMovie(movie_id: Int): Flowable<ResourceData<MoviesEntity>> = notflixImpl.getDetailMovie(movie_id)

    override fun getDetailTv(tv_id: Int): Flowable<ResourceData<TvShowEntity>> = notflixImpl.getDetailTv(tv_id)

    override fun getEpisodes(): Flowable<List<EpisodesEntity>> = notflixImpl.getEpisodes()

    override fun getAllFavMovie(): Flowable<PagedList<MoviesEntity>> = notflixImpl.getAllFavMovie()

    override fun getAllFavTv(): Flowable<PagedList<TvShowEntity>> = notflixImpl.getAllFavTv()
}