package com.example.notflix.core.domain.usecase

import androidx.paging.PagedList
import com.example.notflix.core.data.local.entity.EpisodesEntity
import com.example.notflix.core.data.local.entity.MoviesEntity
import com.example.notflix.core.data.local.entity.TvShowEntity
import com.example.notflix.core.domain.model.MoviesModel
import com.example.notflix.core.domain.model.TvShowModel
import com.example.notflix.core.domain.repository.NotflixImpl
import com.example.notflix.values.ResourceData
import io.reactivex.Flowable

interface NotflixUsecase {
    fun getAllTrendingMovies() : Flowable<ResourceData<PagedList<MoviesModel>>>

    fun getAllPopularTvShow() : Flowable<ResourceData<PagedList<TvShowModel>>>

    fun getDetailMovie(movie_id : Int) : Flowable<ResourceData<MoviesModel>>

    fun getDetailTv(tv_id : Int) : Flowable<ResourceData<TvShowModel>>

    fun getEpisodes() : Flowable<List<EpisodesEntity>>

    fun getAllFavMovie() : Flowable<PagedList<MoviesModel>>

    fun getAllFavTv() : Flowable<PagedList<TvShowModel>>

    fun insertFavMovie(movie: MoviesModel, isFavorite : Boolean)

    fun insertFavTv(tv: TvShowModel, isFavorite: Boolean)
}

class NotflixInteractor(private val notflixImpl: NotflixImpl) : NotflixUsecase{
    override fun getAllTrendingMovies(): Flowable<ResourceData<PagedList<MoviesModel>>> {
        return notflixImpl.getAllTrendingMovies()
    }

    override fun getAllPopularTvShow(): Flowable<ResourceData<PagedList<TvShowModel>>> {
        return notflixImpl.getAllPopularTvShow()
    }

    override fun getDetailMovie(movie_id: Int): Flowable<ResourceData<MoviesModel>> {
        return notflixImpl.getDetailMovie(movie_id)
    }

    override fun getDetailTv(tv_id: Int): Flowable<ResourceData<TvShowModel>> {
        return notflixImpl.getDetailTv(tv_id)
    }

    override fun getEpisodes(): Flowable<List<EpisodesEntity>> {
        return notflixImpl.getEpisodes()
    }

    override fun getAllFavMovie(): Flowable<PagedList<MoviesModel>> {
        return notflixImpl.getAllFavMovie()
    }

    override fun getAllFavTv(): Flowable<PagedList<TvShowModel>> {
        return notflixImpl.getAllFavTv()
    }

    override fun insertFavMovie(movie: MoviesModel, isFavorite: Boolean) {
        notflixImpl.insertFavMovie(movie, isFavorite)
    }

    override fun insertFavTv(tv: TvShowModel, isFavorite: Boolean) {
        notflixImpl.insertFavTv(tv, isFavorite)
    }
}